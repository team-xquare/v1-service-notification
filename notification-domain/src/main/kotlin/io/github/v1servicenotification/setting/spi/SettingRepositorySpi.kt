package io.github.v1servicenotification.setting.spi

import io.github.v1servicenotification.annotation.Spi
import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.setting.Setting
import java.util.UUID

@Spi
interface SettingRepositorySpi {
    fun saveSetting(category: Category, userId: UUID, isActivated: Boolean): Setting
    fun updateSetting(category: Category, userId: UUID, isActivated: Boolean): Setting
    fun settingExist(category: Category, userId: UUID): Boolean
    fun queryActivatedCategory(userId: UUID): List<Category>
}
