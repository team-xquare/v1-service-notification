package io.github.v1servicenotification.category.api

import io.github.v1servicenotification.category.api.response.CategoryListResponse
import java.util.UUID

interface CategoryApi {
    fun queryNotificationCategory(defaultActivated: Boolean): CategoryListResponse
    fun createCategory(title: String, destination: String, defaultActivated: Boolean, topic: String)
    fun removeCategory(categoryId: UUID)
}
