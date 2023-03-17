package io.github.v1servicenotification.detail.api

import io.github.v1servicenotification.detail.api.dto.response.DetailResponse
import java.util.UUID
import io.github.v1servicenotification.detail.api.dto.response.NotificationCountResponse

interface DetailApi {
    fun checkNotification(userId: UUID, notificationId: UUID)
    fun postGroupNotification(categoryId: UUID, title: String, content: String)
    fun postNotification(userId: UUID, categoryId: UUID, title: String, content: String)
    fun queryNotificationDetail(userId: UUID): DetailResponse
    fun queryUnreadNotificationCount(userId: UUID): NotificationCountResponse
}
