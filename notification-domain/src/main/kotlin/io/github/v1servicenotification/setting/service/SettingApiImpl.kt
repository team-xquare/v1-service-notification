package io.github.v1servicenotification.setting.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.api.response.CategoryElement
import io.github.v1servicenotification.category.api.response.CategoryListResponse
import io.github.v1servicenotification.setting.spi.SettingRepositorySpi
import io.github.v1servicenotification.setting.api.SettingApi
import io.github.v1servicenotification.setting.spi.SettingCategorySpi
import java.util.UUID

@DomainService
class SettingApiImpl(
    private val settingRepositorySpi: SettingRepositorySpi,
    private val settingCategorySpi: SettingCategorySpi
) : SettingApi {

    override fun activateCategory(isActivate: Boolean, topic: String, userId: UUID): Int {
        return saveOrUpdateSetting(
            isActivate = isActivate,
            topic = topic,
            userId = userId,
            )
    }

    private fun saveOrUpdateSetting(isActivate: Boolean, topic: String, userId: UUID): Int {
        val category = settingCategorySpi.findByStartingWithTopic(topic)

        return if (settingRepositorySpi.settingExist(category, userId)) {
            settingRepositorySpi.updateAllSetting(category, userId, isActivate)
            204
        } else {
            settingRepositorySpi.saveAllSetting(category, userId, isActivate)
            201
        }
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
