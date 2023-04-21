package io.github.v1servicenotification.category.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.api.CategoryApi
import io.github.v1servicenotification.category.api.response.CategoryElement
import io.github.v1servicenotification.category.api.response.CategoryListResponse
import io.github.v1servicenotification.category.exception.CategoryNotFoundException
import io.github.v1servicenotification.category.spi.QueryCategoryRepositorySpi
import io.github.v1servicenotification.category.spi.UpdateCategoryRepositorySpi
import java.util.UUID

@DomainService
class CategoryApiImpl(
    private val queryCategoryRepositorySpi: QueryCategoryRepositorySpi,
    private val updateCategoryRepositorySpi: UpdateCategoryRepositorySpi
): CategoryApi {

    override fun queryNotificationCategory(defaultActivated: Boolean): CategoryListResponse {
        return CategoryListResponse(
            queryCategoryRepositorySpi.findAllByDefaultActivated(defaultActivated)
                .map { CategoryElement(it.id, it.title, it.destination, it.topic) }
                .toList()
        )
    }

    override fun createCategory(title: String, destination: String, defaultActivated: Boolean, topic: String) {
        updateCategoryRepositorySpi.saveCategory(
            Category(
                title = title,
                destination = destination,
                defaultActivated = defaultActivated,
                topic = topic
            )
        )
    }

    override fun removeCategory(categoryId: UUID) {
        val category = updateCategoryRepositorySpi.findCategoryById(categoryId)
            ?: throw CategoryNotFoundException.EXCEPTION
        updateCategoryRepositorySpi.removeCategory(category)
    }

}
