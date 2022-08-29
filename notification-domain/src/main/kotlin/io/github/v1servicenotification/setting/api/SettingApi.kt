package io.github.v1servicenotification.setting.api

import io.github.v1servicenotification.category.api.response.CategoryListResponse
import java.util.*

interface SettingApi {
    fun activateCategory(categoryId: UUID, userId: UUID): Int
    fun deActivateCategory(categoryId: UUID, userId: UUID): Int
    fun queryActivatedCategory(userId: UUID): CategoryListResponse
}