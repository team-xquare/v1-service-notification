package io.github.v1servicenotification.category.queryCategory.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.queryCategory.api.QueryNotificationCategory
import io.github.v1servicenotification.category.queryCategory.api.dto.response.CategoryElement
import io.github.v1servicenotification.category.queryCategory.api.dto.response.CategoryListResponse
import io.github.v1servicenotification.category.queryCategory.spi.CategoryRepositorySpi

@DomainService
class QueryNotificationCategoryImpl(
    private val categoryRepositorySpi: CategoryRepositorySpi
) : QueryNotificationCategory {

    override fun queryNotificationCategory(): CategoryListResponse {
        return CategoryListResponse(
            categoryRepositorySpi.findAllByDefaultActivatedIsTrue()
                .map { CategoryElement(it.id, it.name, it.destination) }
                .toList()
        )
    }

}