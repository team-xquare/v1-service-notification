package io.github.v1servicenotification.setting.api

import io.github.v1servicenotification.setting.api.response.SettingListResponse
import java.util.UUID

interface SettingApi {
    fun activateCategory(categoryId: UUID, userId: UUID): Int
    fun deActivateCategory(categoryId: UUID, userId: UUID): Int
    fun queryUserCategoryStatus(userId: UUID): SettingListResponse
    fun activateOrDeActivateCategory(isActivate: Boolean, topic: String, userId: UUID)
}
