package io.github.v1servicenotification.category.queryCategory.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.queryCategory.api.QueryNotificationCategory
import io.github.v1servicenotification.category.queryCategory.api.dto.response.CategoryElement
import io.github.v1servicenotification.category.queryCategory.api.dto.response.CategoryListResponse
import io.github.v1servicenotification.category.queryCategory.spi.QueryCategoryRepositorySpi

@DomainService
class QueryNotificationCategoryImpl(
    private val queryCategoryRepositorySpi: QueryCategoryRepositorySpi
) : QueryNotificationCategory {

    override fun queryNotificationCategory(defaultActivated: Boolean): CategoryListResponse {
        return CategoryListResponse(
            queryCategoryRepositorySpi.findAllByDefaultActivated(defaultActivated)
                .map { CategoryElement(it.id, it.name, it.destination) }
                .toList()
        )
    }

}