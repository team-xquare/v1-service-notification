package io.github.v1servicenotification.category.updateCategory.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.updateCategory.api.RemoveCategory
import io.github.v1servicenotification.category.updateCategory.exception.CategoryNotFoundException
import io.github.v1servicenotification.category.updateCategory.spi.UpdateCategoryRepositorySpi
import java.util.*

@DomainService
class RemoveCategoryImpl(
    private val updateCategoryRepositorySpi: UpdateCategoryRepositorySpi
): RemoveCategory {

    override fun removeCategory(categoryId: UUID) {
        val category = updateCategoryRepositorySpi.findCategoryById(categoryId)
            ?: throw CategoryNotFoundException.EXCEPTION
        updateCategoryRepositorySpi.removeCategory(category)
    }

}