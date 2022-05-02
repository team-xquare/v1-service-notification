package io.github.v1servicenotification.domain.setting.presentation

import io.github.v1servicenotification.category.queryCategory.api.dto.response.CategoryListResponse
import io.github.v1servicenotification.setting.activeSetting.api.ActivateCategory
import io.github.v1servicenotification.setting.activeSetting.api.DeActivateCategory
import io.github.v1servicenotification.setting.activeSetting.api.QueryActivatedCategory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/tags")
@RestController
class SettingController(
    private val activateCategory: ActivateCategory,
    private val deActivateCategory: DeActivateCategory,
    private val queryActivatedCategory: QueryActivatedCategory
) {

    @GetMapping
    fun queryActivatedCategory(): CategoryListResponse {
        return queryActivatedCategory.queryActivatedCategory(UUID.randomUUID()) //TODO User Id
    }

    @PatchMapping("/{category-uuid}")
    fun activateNotificationCategory(@PathVariable("category-uuid") categoryId: UUID): ResponseEntity<Unit> {
        return ResponseEntity(
            HttpStatus.valueOf(
                activateCategory.activateCategory(categoryId, UUID.randomUUID()) //TODO User Id
            )
        )
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{category-uuid}")
    fun deActivateNotificationCategory(@PathVariable("category-uuid") categoryId: UUID): ResponseEntity<Unit> {
        return ResponseEntity(
            HttpStatus.valueOf(
                deActivateCategory.deActivateCategory(categoryId, UUID.randomUUID()) //TODO User Id
            )
        )
    }

}