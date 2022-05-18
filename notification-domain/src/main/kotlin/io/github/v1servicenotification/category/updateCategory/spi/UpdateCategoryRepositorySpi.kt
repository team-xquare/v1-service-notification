package io.github.v1servicenotification.category.updateCategory.spi

import io.github.v1servicenotification.annotation.Spi
import io.github.v1servicenotification.category.Category
import java.util.UUID

@Spi
interface UpdateCategoryRepositorySpi {
    fun saveCategory(category: Category)
    fun removeCategory(categoryId: UUID)
}