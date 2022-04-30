package io.github.v1servicenotification.category.queryCategory.api

import io.github.v1servicenotification.category.Category

interface QueryActivatedCategory {
    suspend fun queryActivatedCategory(): List<Category>
}