package io.github.v1servicenotification.setting.spi

import io.github.v1servicenotification.annotation.Spi
import io.github.v1servicenotification.category.Category
import java.util.UUID

@Spi
interface SettingRepositorySpi {
    fun updateAllSetting(categoryIds: List<UUID>, userId: UUID, isActivated: Boolean)
    fun settingExist(categoryIds: List<UUID>, userId: UUID): Boolean
    fun queryActivatedCategory(userId: UUID): List<Category>
}
