package io.github.v1servicenotification.stubs

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.spi.QueryCategoryRepositorySpi
import io.github.v1servicenotification.category.exception.CategoryNotFoundException
import io.github.v1servicenotification.category.spi.UpdateCategoryRepositorySpi
import io.github.v1servicenotification.setting.spi.SettingCategorySpi
import java.util.UUID

class InMemoryCategoryRepository(
    private val categoryMap: HashMap<UUID, Category> = hashMapOf()
) : QueryCategoryRepositorySpi, UpdateCategoryRepositorySpi, SettingCategorySpi {

    override fun saveCategory(category: Category) {
        categoryMap[category.id] = category
    }

    override fun findCategoryById(categoryId: UUID): Category? {
        return categoryMap[categoryId]
    }

    override fun removeCategory(category: Category) {
        categoryMap[category.id]
            ?: throw CategoryNotFoundException.EXCEPTION
        categoryMap.remove(category.id)
    }

    override fun existByTopic(topic: String): Boolean {
        return categoryMap.any { it.value.topic == topic }
    }

    override fun findByTopic(topic: String): Category {
        return categoryMap.values.firstOrNull { it.topic == topic }
            ?: throw CategoryNotFoundException.EXCEPTION
    }

    override fun findAllByDefaultActivated(defaultActivated: Boolean): List<Category> {
        return categoryMap.filter {
            it.value.defaultActivated == defaultActivated
        }.map { it.value }
    }

    override fun findByStartingWithTopic(topic: String): List<Category> {
        return categoryMap.values.filter {
            it.topic.startsWith(topic)
        }
    }
}
