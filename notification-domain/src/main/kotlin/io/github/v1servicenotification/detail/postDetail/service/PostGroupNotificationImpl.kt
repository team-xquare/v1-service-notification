package io.github.v1servicenotification.detail.postDetail.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.queryCategory.spi.QueryCategoryRepositorySpi
import io.github.v1servicenotification.category.updateCategory.exception.CategoryNotFoundException
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
): PostGroupNotification {

    override fun postGroupNotification(categoryId: UUID, title: String, content: String) {
        if(!queryCategoryRepositorySpi.exist(categoryId)) {
            throw CategoryNotFoundException.EXCEPTION
        }

        val userIdList = postDetailSettingRepositorySpi.findAllUserIdByCategoryId(categoryId)

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