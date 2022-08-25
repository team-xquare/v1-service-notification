package io.github.v1servicenotification.setting.facade

import io.github.v1servicenotification.annotation.Facade
import io.github.v1servicenotification.category.spi.QueryCategoryRepositorySpi
import io.github.v1servicenotification.setting.activeSetting.spi.SettingRepositorySpi
import java.util.*

@Facade
class SettingFacade(
    private val queryCategoryRepositorySpi: QueryCategoryRepositorySpi,
    private val settingRepositorySpi: SettingRepositorySpi
) {

    fun saveOrUpdateSetting(categoryId: UUID, userId: UUID, isActivate: Boolean): Int {
        val category = queryCategoryRepositorySpi.findById(categoryId)

        return if (settingRepositorySpi.settingExist(category, userId)) {
            settingRepositorySpi.updateSetting(category, userId, isActivate)
            204
        } else {
            settingRepositorySpi.saveSetting(category, userId, isActivate)
            201
        }
    }

}