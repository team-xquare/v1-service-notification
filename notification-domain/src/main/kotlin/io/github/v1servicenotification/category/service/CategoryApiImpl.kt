package io.github.v1servicenotification.category.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.api.CategoryApi
import io.github.v1servicenotification.category.api.response.CategoryElement
import io.github.v1servicenotification.category.api.response.CategoryListResponse
import io.github.v1servicenotification.category.exception.CategoryNotFoundException
import io.github.v1servicenotification.category.spi.QueryCategoryRepositorySpi
import io.github.v1servicenotification.category.spi.UpdateCategoryRepositorySpi
import java.util.*

@DomainService
class CategoryApiImpl(
    private val queryCategoryRepositorySpi: QueryCategoryRepositorySpi,
    private val updateCategoryRepositorySpi: UpdateCategoryRepositorySpi
): CategoryApi {

    override fun queryNotificationCategory(defaultActivated: Boolean): CategoryListResponse {
        return CategoryListResponse(
            queryCategoryRepositorySpi.findAllByDefaultActivated(defaultActivated)
                .map { CategoryElement(it.id, it.name, it.destination) }
                .toList()
        )
    }

    override fun createCategory(name: String, destination: String, defaultActivated: Boolean) {
        updateCategoryRepositorySpi.saveCategory(
            Category(
                name = name,
                destination = destination,
                defaultActivated = defaultActivated
            )
        )
    }

    override fun removeCategory(categoryId: UUID) {
        val category = updateCategoryRepositorySpi.findCategoryById(categoryId)
            ?: throw CategoryNotFoundException.EXCEPTION
        updateCategoryRepositorySpi.removeCategory(category)
    }

}