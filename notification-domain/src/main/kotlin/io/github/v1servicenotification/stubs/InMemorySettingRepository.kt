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

    override fun saveSetting(category: Category, userId: UUID, isActivated: Boolean): Setting {
        val setting = Setting(userId, category.id, isActivated)
        settingMap[UUID.randomUUID()] = setting

        return setting
    }

    override fun updateSetting(category: Category, userId: UUID, isActivated: Boolean): Setting {
        return settingMap.filter {
            it.value.notificationCategoryId == category.id && it.value.userId == userId
        }.map {
            it.value.changeIsActivate(isActivated)
            it.value
        }[0]
    }

    override fun settingExist(category: Category, userId: UUID): Boolean {
        return settingMap.filter {
            it.value.notificationCategoryId == category.id && it.value.userId == userId
        }.isNotEmpty()
    }


    override fun findAllUserIdByTopicAndIsActivated(topic: String, isActivated: Boolean): List<UUID> {
        return settingMap.values.filter {
            it.isActivated == isActivated && categoryMap[it.notificationCategoryId]?.topic == topic
        }.map { it.userId }
    }

    override fun queryUserIdSetting(userId: UUID): List<Setting> {
        return settingMap.filter { it.value.userId == userId }.map { it.value }
    }

    override fun queryUserCategory(userId: UUID): List<Category> {
        return settingMap.filter { it.value.userId == userId }.map {
            categoryMap[it.value.notificationCategoryId] ?: throw RuntimeException()
        }
    }
}
