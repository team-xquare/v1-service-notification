package io.github.v1servicenotification.stubs

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.queryCategory.spi.QueryCategoryRepositorySpi
import io.github.v1servicenotification.category.updateCategory.exception.CategoryNotFoundException
import io.github.v1servicenotification.category.updateCategory.spi.UpdateCategoryRepositorySpi
import java.util.*

class InMemoryCategoryRepository(
    private val categoryMap: HashMap<UUID, Category> = hashMapOf()
) : QueryCategoryRepositorySpi, UpdateCategoryRepositorySpi {

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

    override fun findById(id: UUID): Category {
        return categoryMap[id]
            ?: throw NullPointerException()
    }

    override fun findAllByDefaultActivatedIsTrue(): List<Category> {
        return categoryMap.filter {
            it.value.defaultActivated
        }.map { it.value }
    }

}