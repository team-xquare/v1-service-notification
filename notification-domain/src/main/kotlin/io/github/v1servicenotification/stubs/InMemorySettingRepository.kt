package io.github.v1servicenotification.stubs

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.updateCategory.exception.CategoryNotFoundException
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailSettingRepositorySpi
import io.github.v1servicenotification.setting.Setting
import io.github.v1servicenotification.setting.activeSetting.spi.SettingRepositorySpi
import java.util.*
import kotlin.collections.HashMap

class InMemorySettingRepository(
    private val categoryMap: HashMap<UUID, Category> = hashMapOf(),
    private val settingMap: HashMap<UUID, Setting> = hashMapOf()
) : SettingRepositorySpi, PostDetailSettingRepositorySpi {

    fun saveCategory(category: Category) {
        categoryMap[category.id] = category
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

    override fun queryActivatedCategory(userId: UUID): List<Category> {
        return settingMap.filter {
            it.value.userId == userId && it.value.isActivated
        }.map {
            categoryMap[it.value.notificationCategoryId]
                ?: throw CategoryNotFoundException.EXCEPTION
        }
    }

    override fun findAllUserIdByCategoryId(categoryId: UUID): List<UUID> {
        return settingMap.filter {
            it.value.notificationCategoryId == categoryId
        }.map {
            it.value.userId
        }
    }

}