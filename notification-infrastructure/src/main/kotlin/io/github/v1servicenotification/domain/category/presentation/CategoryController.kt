package io.github.v1servicenotification.domain.category.presentation

import io.github.v1servicenotification.category.queryCategory.api.QueryNotificationCategory
import io.github.v1servicenotification.category.queryCategory.api.dto.response.CategoryListResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryController(
    private val queryNotificationCategory: QueryNotificationCategory,
) {

    @GetMapping("/categories")
    fun queryNotificationCategoryList(): CategoryListResponse {
        return queryNotificationCategory.queryNotificationCategory()
    }

}