package io.github.v1servicenotification.category.api

import io.github.v1servicenotification.category.Topic
import io.github.v1servicenotification.category.api.response.CategoryListResponse
import java.util.UUID

interface CategoryApi {
    fun queryNotificationCategory(defaultActivated: Boolean): CategoryListResponse
    fun createCategory(name: String, destination: String, defaultActivated: Boolean, topic: Topic)
    fun removeCategory(categoryId: UUID)
}
