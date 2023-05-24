package io.github.v1servicenotification.setting.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.api.response.CategoryElement
import io.github.v1servicenotification.category.api.response.CategoryListResponse
import io.github.v1servicenotification.setting.spi.SettingRepositorySpi
import io.github.v1servicenotification.setting.api.SettingApi
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

    override fun queryActivatedCategory(userId: UUID): CategoryListResponse {
        return CategoryListResponse(
            settingRepositorySpi.queryActivatedCategory(userId)
                .map {
                    CategoryElement(
                        id = it.id,
                        title = it.title,
                        destination = it.destination,
                        topic = it.topic
                    )
                }
                .toList()
        )
    }
}
