package io.github.v1servicenotification.detail.postDetail.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.queryCategory.spi.QueryCategoryRepositorySpi
import io.github.v1servicenotification.category.updateCategory.exception.CategoryNotFoundException
import io.github.v1servicenotification.detail.Detail
import io.github.v1servicenotification.detail.postDetail.api.PostNotification
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailFcmSpi
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailRepositorySpi
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailUserSpi
import java.time.LocalDateTime
import java.util.*

@DomainService
class PostNotificationImpl(
    private val postDetailRepositorySpi: PostDetailRepositorySpi,
    private val postDetailFcmSpi: PostDetailFcmSpi,
    private val queryCategoryRepositorySpi: QueryCategoryRepositorySpi,
    private val postDetailUserSpi: PostDetailUserSpi
): PostNotification {

    override fun postNotification(userId: UUID, categoryId: UUID, title: String, content: String) {
        if(!queryCategoryRepositorySpi.exist(categoryId)) {
            throw CategoryNotFoundException.EXCEPTION
        }

        postDetailRepositorySpi.save(
            Detail(
                title = title,
                content = content,
                sentAt = LocalDateTime.now(),
                isRead = false,
                userId = userId,
                categoryId = categoryId
            )
        )

        postDetailFcmSpi.sendMessage(postDetailUserSpi.getDeviceToken(userId), title, content)
    }
}