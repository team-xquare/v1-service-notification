package io.github.v1servicenotification.stubs

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.detail.spi.PostDetailSettingRepositorySpi
import io.github.v1servicenotification.setting.Setting
import io.github.v1servicenotification.setting.spi.SettingRepositorySpi
import java.util.UUID

class InMemorySettingRepository(
    private val categoryMap: HashMap<UUID, Category> = hashMapOf(),
    private val settingMap: HashMap<UUID, Setting> = hashMapOf()
) : SettingRepositorySpi, PostDetailSettingRepositorySpi {

    fun saveCategory(category: Category) {
        categoryMap[category.id] = category
    }

    fun findSetting(userId: UUID, categoryId: UUID): Setting? {
        return settingMap
            .filter { it.value.userId == userId && it.value.notificationCategoryId == categoryId }
            .map { it.value }.firstOrNull()
    }

    override fun saveSetting(categories: List<Category>, userId: UUID, isActivated: Boolean): List<Setting> {
        return categories.map {
            val setting = Setting(
                userId = userId,
                notificationCategoryId = it.id,
                isActivated = isActivated
            )
            settingMap[setting.notificationCategoryId] = setting
            setting
        }
    }

    override fun updateSetting(categories: List<Category>, userId: UUID, isActivated: Boolean): List<Setting> {
        return categories.map {
            val setting = findSetting(userId, it.id)
            setting?.changeIsActivate(isActivated)
            setting
        }.filterNotNull()
    }

    override fun settingExist(categories: List<Category>, userId: UUID): Boolean {
        return categories.mapNotNull {
            findSetting(userId, it.id)
        }.isNotEmpty()
    }

    override fun queryActivatedCategory(userId: UUID): List<Category> {
        return categoryMap.filter {
            val setting = findSetting(userId, it.value.id)
            setting?.isActivated ?: it.value.defaultActivated
        }.map { it.value }
    }

    override fun findAllUserIdByTopicAndIsActivated(topic: String, isActivated: Boolean): List<UUID> {
        return settingMap.values.filter {
            it.isActivated == isActivated && categoryMap[it.notificationCategoryId]?.topic == topic
        }.map { it.userId }
    }
}
