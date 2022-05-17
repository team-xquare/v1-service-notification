package io.github.v1servicenotification.category.updateCategory.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.updateCategory.api.CreateCategory
import io.github.v1servicenotification.category.updateCategory.api.dto.request.CreateCategoryRequest
import io.github.v1servicenotification.category.updateCategory.spi.UpdateCategoryRepositorySpi
import java.util.*

@DomainService
class CreateCategoryImpl(
    private val updateCategoryRepositorySpi: UpdateCategoryRepositorySpi
): CreateCategory {

    override fun createCategory(request: CreateCategoryRequest) {
        updateCategoryRepositorySpi.saveCategory(
            Category(
                id = UUID.randomUUID(),
                name = request.name,
                destination = request.destination,
                defaultActivated = request.defaultActivated
            )
        )
    }

}