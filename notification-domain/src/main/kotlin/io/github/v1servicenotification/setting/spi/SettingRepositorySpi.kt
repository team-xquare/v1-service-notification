package io.github.v1servicenotification.setting.spi

import io.github.v1servicenotification.annotation.Spi
import io.github.v1servicenotification.category.Category
import java.util.UUID

@Spi
interface SettingRepositorySpi {
    fun saveSetting(category: Category, userId: UUID, isActivated: Boolean): Setting
    fun updateSetting(category: Category, userId: UUID, isActivated: Boolean): Setting
    fun settingExist(category: Category, userId: UUID): Boolean
    fun queryUserIdSetting(userId: UUID): List<Setting>
    fun queryUserCategory(userId: UUID): List<Category>
    fun updateAllSetting(categoryIds: List<UUID>, userId: UUID, isActivated: Boolean)
    fun settingExist(categoryIds: List<UUID>, userId: UUID): Boolean
    fun queryActivatedCategory(userId: UUID): List<Category>
}
