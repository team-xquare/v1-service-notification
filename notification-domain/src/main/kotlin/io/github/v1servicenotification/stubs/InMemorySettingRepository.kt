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


    override fun updateAllSetting(categoryIds: List<UUID>, userId: UUID, isActivated: Boolean) {
        categoryIds.forEach {
            val setting = findSetting(userId, it)
            setting?.changeIsActivate(isActivated)
        }
    }

    override fun settingExist(categoryIds: List<UUID>, userId: UUID): Boolean {
        return categoryIds.mapNotNull {
            findSetting(userId, it)
        }.isNotEmpty()
    }

    override fun queryActivatedCategory(userId: UUID): List<Category> {
        return categoryMap.filter {
            val setting = findSetting(userId, it.value.id)
            setting?.isActivated ?: it.value.defaultActivated
        }.map { it.value }
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
}
