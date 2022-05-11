package io.github.v1servicenotification.stubs

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.queryCategory.spi.CategoryRepositorySpi
import io.github.v1servicenotification.setting.Setting
import io.github.v1servicenotification.setting.activeSetting.spi.SettingRepositorySpi
import java.util.*

class SettingId(
    val userId: UUID,
    val categoryId: UUID
) {
    override fun equals(other: Any?): Boolean {
        return if (other is SettingId) {
            this.userId == other.userId && this.categoryId == other.categoryId
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        var result = userId.hashCode()
        result = 31 * result + categoryId.hashCode()
        return result
    }
}

class InMemorySettingRepository(
    private val categoryRepositorySpi: CategoryRepositorySpi,
    private val settingMap: HashMap<SettingId, Setting> = hashMapOf()
) : SettingRepositorySpi {

    override fun saveSetting(category: Category, userId: UUID, isActivated: Boolean): Setting {
        val setting = Setting(userId, category.id, isActivated)
        val settingId = SettingId(userId, category.id)
        settingMap[settingId] = setting

        return setting
    }

    override fun updateSetting(category: Category, userId: UUID, isActivated: Boolean): Setting {
        val settingId = SettingId(userId, category.id)
        return settingMap.filter {
            it.key == SettingId(userId, category.id)
        }.map {
            it.value.changeIsActivate(isActivated)
            settingMap[settingId] = it.value
            it.value
        }[0]
    }

    override fun settingExist(category: Category, userId: UUID): Boolean {
        val settingId = SettingId(userId, category.id)
        return settingMap.filter {
            it.key == settingId
        }.isNotEmpty()
    }

    override fun queryActivatedCategory(userId: UUID): List<Category> {
        return settingMap.filter {
            it.key.userId == userId && it.value.isActivated
        }.map {
            categoryRepositorySpi.findById(it.value.notificationCategoryId)
        }
    }

}