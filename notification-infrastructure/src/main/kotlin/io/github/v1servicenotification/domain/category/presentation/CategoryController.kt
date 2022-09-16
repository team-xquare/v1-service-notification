package io.github.v1servicenotification.domain.category.presentation

import io.github.v1servicenotification.category.api.CategoryApi
import io.github.v1servicenotification.category.api.response.CategoryListResponse
import io.github.v1servicenotification.domain.category.presentation.dto.request.CreateCategoryRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@Tag(name = "알림 카테고리 설정", description = "알림 카테고리 관련 Api 입니다.")
@RequestMapping("/categories")
@RestController
class CategoryController(
    private val categoryApi: CategoryApi
) {

    @Operation(summary = "알림 카테고리 목록")
    @GetMapping
    fun queryNotificationCategoryList(@RequestParam("default_activated") defaultActivated: Boolean): CategoryListResponse {
        return categoryApi.queryNotificationCategory(defaultActivated)
    }

    @Operation(summary = "알림 카테고리 생성")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createCategory(@RequestBody request: CreateCategoryRequest) {
        categoryApi.createCategory(
            name = request.name,
            destination = request.destination,
            defaultActivated = request.defaultActivated
        )
    }

    @Operation(summary = "알림 카테고리 제거")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{category-uuid}")
    fun removeCategory(@PathVariable("category-uuid") categoryId: UUID) {
        categoryApi.removeCategory(categoryId)
    }

}