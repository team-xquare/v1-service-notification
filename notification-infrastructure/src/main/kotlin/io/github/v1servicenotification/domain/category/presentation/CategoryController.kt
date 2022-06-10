package io.github.v1servicenotification.domain.category.presentation

import io.github.v1servicenotification.category.queryCategory.api.QueryNotificationCategory
import io.github.v1servicenotification.category.queryCategory.api.dto.response.CategoryListResponse
import io.github.v1servicenotification.category.updateCategory.api.CreateCategory
import io.github.v1servicenotification.category.updateCategory.api.RemoveCategory
import io.github.v1servicenotification.domain.category.presentation.dto.request.CreateCategoryRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RequestMapping("/categories")
@RestController
class CategoryController(
    private val queryNotificationCategory: QueryNotificationCategory,
    private val createCategory: CreateCategory,
    private val removeCategory: RemoveCategory
) {

    @GetMapping
    fun queryNotificationCategoryList(): CategoryListResponse {
        return queryNotificationCategory.queryNotificationCategory()
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createCategory(@RequestBody request: CreateCategoryRequest) {
        createCategory.createCategory(
            name = request.name,
            destination = request.destination,
            defaultActivated = request.defaultActivated
        )
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{category-uuid}")
    fun removeCategory(@PathVariable("category-uuid") categoryId: UUID) {
        removeCategory.removeCategory(categoryId)
    }

}