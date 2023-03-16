package io.github.v1servicenotification.detail.api

import io.github.v1servicenotification.detail.api.dto.response.DetailResponse
import io.github.v1servicenotification.detail.api.dto.response.NotificationCountResponse
import java.util.*

interface DetailApi {
    fun checkNotification(userId: UUID, notificationId: UUID)
    fun postGroupNotification(categoryId: UUID, title: String, content: String)
    fun postNotification(userId: UUID, categoryId: UUID, title: String, content: String)
    fun queryNotificationDetail(userId: UUID): DetailResponse
    fun queryUnreadNotificationCount(userId: UUID): NotificationCountResponse
}