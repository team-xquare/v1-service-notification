package io.github.v1servicenotification.category.queryCategory.api

import io.github.v1servicenotification.category.Category

interface QueryNotificationCategory {
    suspend fun queryNotificationCategory(): List<Category>
}