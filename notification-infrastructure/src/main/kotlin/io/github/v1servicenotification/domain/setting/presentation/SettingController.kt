package io.github.v1servicenotification.domain.setting.presentation

import io.github.v1servicenotification.category.api.response.CategoryListResponse
import io.github.v1servicenotification.global.extension.getUserId
import io.github.v1servicenotification.setting.api.SettingApi

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/tags")
@RestController
class SettingController(
    private val settingApi: SettingApi
) {

    @GetMapping
    fun queryActivatedCategory(): CategoryListResponse {
        return settingApi.queryActivatedCategory(getUserId())
    }

    @PatchMapping("/{category-uuid}")
    fun activateNotificationCategory(@PathVariable("category-uuid") categoryId: UUID): ResponseEntity<Unit> {
        return ResponseEntity(
            HttpStatus.valueOf(
                settingApi.activateCategory(categoryId, getUserId())
            )
        )
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{category-uuid}")
    fun deActivateNotificationCategory(@PathVariable("category-uuid") categoryId: UUID): ResponseEntity<Unit> {
        return ResponseEntity(
            HttpStatus.valueOf(
                settingApi.deActivateCategory(categoryId, getUserId())
            )
        )
    }

}