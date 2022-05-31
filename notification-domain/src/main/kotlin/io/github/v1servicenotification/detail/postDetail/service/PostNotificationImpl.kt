package io.github.v1servicenotification.detail.postDetail.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.detail.Detail
import io.github.v1servicenotification.detail.postDetail.api.PostNotification
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailFcmSpi
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailRepositorySpi
import java.time.LocalDateTime
import java.util.*

@DomainService
class PostNotificationImpl(
    private val postDetailRepositorySpi: PostDetailRepositorySpi,
    private val postDetailFcmSpi: PostDetailFcmSpi
): PostNotification {

    override fun postNotification(userId: UUID, categoryId: UUID, title: String, content: String) {
        //TODO User service에 token 받아오는 spi


        postDetailRepositorySpi.save(
            Detail(
                id = UUID.randomUUID(),
                title = title,
                content = content,
                sentAt = LocalDateTime.now(),
                isRead = false,
                userId = userId,
                categoryId = categoryId
            )
        )

        postDetailFcmSpi.sendMessage("", title, content)
    }
}