package io.github.v1servicenotification.category.queryCategory.api

import io.github.v1servicenotification.category.queryCategory.api.dto.response.CategoryListResponse

interface QueryNotificationCategory {
    fun queryNotificationCategory(defaultActivated: Boolean): CategoryListResponse
}