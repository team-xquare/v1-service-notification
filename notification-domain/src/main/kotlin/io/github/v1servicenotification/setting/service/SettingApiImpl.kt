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
): SettingApi {

    override fun activateCategory(categoryId: UUID, userId: UUID): Int {
        return saveOrUpdateSetting(
            categoryId = categoryId,
            userId = userId,
            isActivate = true
        )
    }

    override fun deActivateCategory(categoryId: UUID, userId: UUID): Int {
        return saveOrUpdateSetting(
            categoryId = categoryId,
            userId = userId,
            isActivate = false
        )
    }

    private fun saveOrUpdateSetting(categoryId: UUID, userId: UUID, isActivate: Boolean): Int {
        val category = settingCategorySpi.findById(categoryId)

        return if (settingRepositorySpi.settingExist(category, userId)) {
            settingRepositorySpi.updateSetting(category, userId, isActivate)
            204
        } else {
            settingRepositorySpi.saveSetting(category, userId, isActivate)
            201
        }
    }

    override fun queryActivatedCategory(userId: UUID): CategoryListResponse {
        return CategoryListResponse(
            settingRepositorySpi.queryActivatedCategory(userId)
                .map {
                    CategoryElement(
                        id = it.id,
                        name = it.name,
                        destination = it.destination
                    )
                }
                .toList()
        )
    }
}
