package io.github.v1servicenotification.category.queryCategory.api

import io.github.v1servicenotification.category.NotificationCategory

interface QueryActivatedCategory {
    suspend fun queryActivatedCategory(): List<NotificationCategory>
}