package io.github.v1servicenotification.setting.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.setting.spi.SettingRepositorySpi
import io.github.v1servicenotification.setting.api.SettingApi
import io.github.v1servicenotification.setting.api.response.SettingElement
import io.github.v1servicenotification.setting.api.response.SettingListResponse
import io.github.v1servicenotification.setting.exception.SettingNotFoundException
import io.github.v1servicenotification.setting.spi.SettingCategorySpi
import java.util.UUID

@DomainService
class SettingApiImpl(
    private val settingRepositorySpi: SettingRepositorySpi,
    private val settingCategorySpi: SettingCategorySpi
) : SettingApi {

    override fun activateOrDeActivateCategory(isActivate: Boolean, topic: String, userId: UUID) {
        val category = settingCategorySpi.findByStartingWithTopic(topic)

        if (!settingRepositorySpi.settingExist(category, userId)) {
            throw SettingNotFoundException.EXCEPTION
        }
        settingRepositorySpi.updateAllSetting(category, userId, isActivate)
    }

    override fun queryUserCategoryStatus(userId: UUID): SettingListResponse {
        val settingList = settingRepositorySpi.queryUserIdSetting(userId)
        val categoryList = settingRepositorySpi.queryUserCategory(userId)

        val categoryMap = categoryList.associateBy { it.id }

        val activatedCategories = settingList.mapNotNull { setting ->
            val category = categoryMap[setting.notificationCategoryId]
            category?.let {
                SettingElement(it.topic, setting.isActivated)
            }
        }.distinctBy { it.topic }

        return SettingListResponse(activatedCategories)
    }
}
