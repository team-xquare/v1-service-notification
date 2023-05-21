package io.github.v1servicenotification.setting.spi

import io.github.v1servicenotification.annotation.Spi
import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.setting.Setting
import java.util.UUID

@Spi
interface SettingRepositorySpi {
    fun saveSetting(categories: List<Category>, userId: UUID, isActivated: Boolean): List<Setting>
    fun updateSetting(categories: List<Category>, userId: UUID, isActivated: Boolean): List<Setting>
    fun settingExist(categories: List<Category>, userId: UUID): Boolean
    fun queryActivatedCategory(userId: UUID): List<Category>
}
