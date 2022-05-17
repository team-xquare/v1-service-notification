package io.github.v1servicenotification.stubs

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.queryCategory.spi.QueryCategoryRepositorySpi
import java.util.*

class InMemoryQueryCategoryRepository(
    private val categoryMap: HashMap<UUID, Category> = hashMapOf()
) : QueryCategoryRepositorySpi {

    fun saveCategory(category: Category) {
        categoryMap[category.id] = category
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