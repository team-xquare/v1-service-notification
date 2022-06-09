package io.github.v1servicenotification.category.updateCategory.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.updateCategory.api.CreateCategory
import io.github.v1servicenotification.category.updateCategory.spi.UpdateCategoryRepositorySpi
import java.util.*

@DomainService
class CreateCategoryImpl(
    private val updateCategoryRepositorySpi: UpdateCategoryRepositorySpi
): CreateCategory {

    override fun createCategory(name: String, destination: String, defaultActivated: Boolean) {
        updateCategoryRepositorySpi.saveCategory(
            Category(
                id = UUID.randomUUID(),
                name = name,
                destination = destination,
                defaultActivated = defaultActivated
            )
        )
    }

}