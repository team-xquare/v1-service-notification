package io.github.v1servicenotification.setting.api

import io.github.v1servicenotification.category.api.response.CategoryListResponse
import java.util.UUID

interface SettingApi {
    fun activateOrDeActivateCategory(isActivate: Boolean, topic: String, userId: UUID)
    fun queryActivatedCategory(userId: UUID): CategoryListResponse
}
