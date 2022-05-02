package io.github.v1servicenotification.setting.activeSetting.spi

import io.github.v1servicenotification.annotation.Spi
import io.github.v1servicenotification.category.Category
import java.util.UUID

@Spi
interface SettingRepositorySpi {
    fun saveSetting(category: Category, userId: UUID, isActivated: Boolean)
    fun updateSetting(category: Category, userId: UUID, isActivated: Boolean)
    fun settingExist(category: Category, userId: UUID): Boolean
    fun queryActivatedCategory(userId: UUID): List<Category>
}