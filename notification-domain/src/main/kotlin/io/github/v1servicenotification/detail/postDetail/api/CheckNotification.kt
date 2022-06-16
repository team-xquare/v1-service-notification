package io.github.v1servicenotification.detail.postDetail.api

import java.util.UUID

interface CheckNotification {
    fun checkNotification(userId: UUID, notificationId: UUID)
}