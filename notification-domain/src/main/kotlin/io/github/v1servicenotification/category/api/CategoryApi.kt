package io.github.v1servicenotification.category.api

import io.github.v1servicenotification.category.api.response.CategoryListResponse
import java.util.*

interface CategoryApi {
    fun queryNotificationCategory(defaultActivated: Boolean): CategoryListResponse
    fun createCategory(name: String, destination: String, defaultActivated: Boolean)
    fun removeCategory(categoryId: UUID)
}