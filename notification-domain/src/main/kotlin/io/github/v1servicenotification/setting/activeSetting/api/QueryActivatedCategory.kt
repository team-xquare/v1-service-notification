package io.github.v1servicenotification.setting.activeSetting.api

import io.github.v1servicenotification.category.queryCategory.api.dto.response.CategoryListResponse
import java.util.*

interface QueryActivatedCategory {
    fun queryActivatedCategory(userId: UUID): CategoryListResponse
}