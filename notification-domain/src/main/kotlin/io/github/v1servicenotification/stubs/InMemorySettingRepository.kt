package io.github.v1servicenotification.stubs

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.exception.CategoryNotFoundException
import io.github.v1servicenotification.detail.spi.PostDetailSettingRepositorySpi
import io.github.v1servicenotification.setting.Setting
import io.github.v1servicenotification.setting.spi.SettingRepositorySpi
import java.util.UUID

class InMemorySettingRepository(
    private val categoryMap: HashMap<UUID, Category> = hashMapOf(),
    private val settingMap: HashMap<UUID, Setting> = hashMapOf()
) : SettingRepositorySpi, PostDetailSettingRepositorySpi {


    fun saveSetting(category: Category, userId: UUID, isActivated: Boolean): Setting {
        val setting = Setting(userId, category.id, isActivated)
        settingMap[UUID.randomUUID()] = setting

        return setting
    }

    fun saveCategory(category: Category) {
        categoryMap[category.id] = category
    }


    override fun updateAllSetting(categoryIds: List<UUID>, userId: UUID, isActivated: Boolean) {
        categoryIds.forEach {
            val setting = findSetting(userId, it)
            setting?.changeIsActivate(isActivated)
        }
    }

    override fun settingExist(categoryIds: List<UUID>, userId: UUID): Boolean {
        return categoryIds.map { findSetting(userId, it) }.any { it != null }
    }

    override fun queryUserIdSetting(userId: UUID): List<Setting> {
        return settingMap.values.filter { it.userId == userId }
    }

    private fun findSetting(userId: UUID, categoryId: UUID): Setting? {
        return settingMap
            .filter { it.value.userId == userId && it.value.notificationCategoryId == categoryId }
            .map { it.value }.firstOrNull()
    }

    override fun findAllUserIdByTopicAndIsActivated(topic: String, isActivated: Boolean): List<UUID> {
        return settingMap.values.filter {
            it.isActivated == isActivated && categoryMap[it.notificationCategoryId]?.topic == topic
        }.map { it.userId }
    }

    override fun queryUserCategory(userId: UUID): List<Category> {
        return settingMap.filter { it.value.userId == userId }.map {
            categoryMap[it.value.notificationCategoryId] ?: throw CategoryNotFoundException.EXCEPTION
        }
    }

    override fun findIsActivatedByUserIdAndTopic(userId: UUID, topic: String): Boolean {
        return settingMap.values.filter { it.userId == userId && categoryMap[it.notificationCategoryId]?.topic == topic }.all { it.isActivated }
    }
}
