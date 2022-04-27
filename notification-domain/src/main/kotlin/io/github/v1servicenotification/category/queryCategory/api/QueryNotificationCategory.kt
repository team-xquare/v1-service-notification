package io.github.v1servicenotification.category.queryCategory.api

import io.github.v1servicenotification.category.NotificationCategory

interface QueryNotificationCategory {
    suspend fun queryNotificationCategory(): List<NotificationCategory>
}