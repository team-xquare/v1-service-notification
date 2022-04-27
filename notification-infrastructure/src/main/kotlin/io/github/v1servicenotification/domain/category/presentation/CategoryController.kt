package io.github.v1servicenotification.domain.category.presentation

import io.github.v1servicenotification.domain.category.presentation.dto.response.NotificationCategoryListResponse
import io.github.v1servicenotification.domain.category.service.QueryActivatedCategory
import io.github.v1servicenotification.domain.category.service.QueryNotificationCategoryListService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryController(
        private val queryNotificationCategoryListService: QueryNotificationCategoryListService,
        private val queryActivatedCategory: QueryActivatedCategory
) {

    @GetMapping("/categories")
    fun queryNotificationCategoryList(): NotificationCategoryListResponse {
        return queryNotificationCategoryListService.execute()
    }

    @GetMapping("/tags")
    fun queryActivatedCategoryList(): NotificationCategoryListResponse {
        return queryActivatedCategory.execute()
    }

}