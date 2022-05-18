package io.github.v1servicenotification.category.updateCategory.api

import io.github.v1servicenotification.category.updateCategory.api.dto.request.CreateCategoryRequest

interface CreateCategory {
    fun createCategory(request: CreateCategoryRequest)
}