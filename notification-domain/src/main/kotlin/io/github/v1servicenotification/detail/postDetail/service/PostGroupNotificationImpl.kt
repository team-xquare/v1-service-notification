package io.github.v1servicenotification.detail.postDetail.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.queryCategory.spi.QueryCategoryRepositorySpi
import io.github.v1servicenotification.detail.Detail
import io.github.v1servicenotification.detail.postDetail.api.PostGroupNotification
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailFcmSpi
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailRepositorySpi
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailSettingRepositorySpi
import java.time.LocalDateTime
import java.util.*

@DomainService
class PostGroupNotificationImpl(
    private val postDetailSettingRepositorySpi: PostDetailSettingRepositorySpi,
    private val postDetailRepositorySpi: PostDetailRepositorySpi,
    private val queryCategoryRepositorySpi: QueryCategoryRepositorySpi,
    private val postDetailFcmSpi: PostDetailFcmSpi
): PostGroupNotification {

    override fun postGroupNotification(categoryId: UUID, title: String, content: String) {
        val category = queryCategoryRepositorySpi.findById(categoryId)
        val settingList = postDetailSettingRepositorySpi.findSettingByCategory(category)

        //TODO User service에 token 받아오는 spi

        val detailList = settingList
            .stream()
            .map {
                Detail(
                    id = UUID.randomUUID(),
                    title = title,
                    content = content,
                    sentAt = LocalDateTime.now(),
                    isRead = false,
                    userId = it.userId,
                    categoryId = categoryId
                )
            }.toList()

        postDetailRepositorySpi.saveAllDetail(detailList)

        postDetailFcmSpi.sendMessageByUserIdList(emptyList(), title, content)

    }

}