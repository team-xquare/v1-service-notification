package io.github.v1servicenotification.detail.postDetail.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.detail.postDetail.api.CheckNotification
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailRepositorySpi
import java.util.*

@DomainService
class CheckNotificationImpl(
    private val postDetailRepositorySpi: PostDetailRepositorySpi
): CheckNotification {

    override fun checkNotification(userId: UUID, notificationId: UUID) {
        postDetailRepositorySpi.checkDetailByUserIdAndDetailId(userId, notificationId)
    }

}