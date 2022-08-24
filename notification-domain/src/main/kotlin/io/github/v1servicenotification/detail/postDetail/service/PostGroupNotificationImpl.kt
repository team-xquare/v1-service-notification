package io.github.v1servicenotification.detail.postDetail.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.spi.QueryCategoryRepositorySpi
import io.github.v1servicenotification.category.exception.CategoryNotFoundException
import io.github.v1servicenotification.detail.Detail
import io.github.v1servicenotification.detail.postDetail.api.PostGroupNotification
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailFcmSpi
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailRepositorySpi
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailSettingRepositorySpi
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailUserSpi
import java.time.LocalDateTime
import java.util.*

@DomainService
class PostGroupNotificationImpl(
    private val postDetailSettingRepositorySpi: PostDetailSettingRepositorySpi,
    private val postDetailRepositorySpi: PostDetailRepositorySpi,
    private val queryCategoryRepositorySpi: QueryCategoryRepositorySpi,
    private val postDetailFcmSpi: PostDetailFcmSpi,
    private val postDetailUserSpi: PostDetailUserSpi
) : PostGroupNotification {

    override fun postGroupNotification(categoryId: UUID, title: String, content: String) {
        if (!queryCategoryRepositorySpi.exist(categoryId)) {
            throw CategoryNotFoundException.EXCEPTION
        }

        val userIdList = if (queryCategoryRepositorySpi.findById(categoryId).defaultActivated) {
            // 기본값이 true면 Setting에서 false로 설정한 사람을 제외하고 발송한다.
            postDetailUserSpi.getExcludeUserIdList(
                postDetailSettingRepositorySpi.findAllUserIdByCategoryIdAndIsActivated(categoryId, false)
            )
        } else {
            postDetailSettingRepositorySpi.findAllUserIdByCategoryIdAndIsActivated(categoryId, true)
        }


        val detailList = userIdList
            .stream()
            .map {
                Detail(
                    title = title,
                    content = content,
                    sentAt = LocalDateTime.now(),
                    isRead = false,
                    userId = it,
                    categoryId = categoryId
                )
            }.toList()

        postDetailRepositorySpi.saveAllDetail(detailList)

        postDetailFcmSpi.sendGroupMessage(postDetailUserSpi.getDeviceTokenList(userIdList), title, content)

    }

}