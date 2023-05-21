package io.github.v1servicenotification.setting.api

import io.github.v1servicenotification.category.api.response.CategoryListResponse
import java.util.UUID

interface SettingApi {
    fun activateCategory(isActivate: Boolean, topic: String, userId: UUID): Int
    fun queryActivatedCategory(userId: UUID): CategoryListResponse
}
