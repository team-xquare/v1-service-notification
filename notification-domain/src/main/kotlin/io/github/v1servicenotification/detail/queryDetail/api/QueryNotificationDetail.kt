package io.github.v1servicenotification.detail.queryDetail.api

import io.github.v1servicenotification.detail.queryDetail.api.dto.NotificationDetailResponse

interface QueryNotificationDetail {
    suspend fun queryNotificationDetail(): List<NotificationDetailResponse>
}