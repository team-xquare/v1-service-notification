package io.github.v1servicenotification.detail.api

import io.github.v1servicenotification.detail.api.dto.response.DetailResponse
import java.util.UUID
import io.github.v1servicenotification.detail.api.dto.response.NotificationCountResponse

interface NotificationDetailApi {
    fun postGroupNotification(topic: String, content: String, threadId: String)
    fun postNotification(userId: UUID, topic: String, content: String,threadId: String)
    fun queryNotificationDetail(userId: UUID): DetailResponse
    fun queryUnreadNotificationCount(userId: UUID): NotificationCountResponse
}
