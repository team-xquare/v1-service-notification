package io.github.v1servicenotification.setting.api

import io.github.v1servicenotification.setting.api.response.SettingListResponse
import java.util.UUID

interface SettingApi {
    fun queryUserCategoryStatus(userId: UUID): SettingListResponse
    fun activateOrDeActivateCategory(isActivate: Boolean, topic: String, userId: UUID)
}
