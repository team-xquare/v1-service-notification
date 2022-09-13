package io.github.v1servicenotification.domain.setting.presentation

import io.github.v1servicenotification.category.api.response.CategoryListResponse
import io.github.v1servicenotification.global.extension.getUserId
import io.github.v1servicenotification.setting.api.SettingApi
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@Tag(name = "알림 카테고리 활성화 설정", description = "알림 카테고리 활성화 관련 Api 입니다.")
@RequestMapping("/tags")
@RestController
class SettingController(
    private val settingApi: SettingApi
) {

    @Operation(summary = "활성화된 알림 카테고리 목록")
    @GetMapping
    fun queryActivatedCategory(): CategoryListResponse {
        return settingApi.queryActivatedCategory(getUserId())
    }

    @Operation(summary = "알림 카테고리 활성화")
    @PatchMapping("/{category-uuid}")
    fun activateNotificationCategory(@PathVariable("category-uuid") categoryId: UUID): ResponseEntity<Unit> {
        return ResponseEntity(
            HttpStatus.valueOf(
                settingApi.activateCategory(categoryId, getUserId())
            )
        )
    }

    @Operation(summary = "알림 카테고리 비활성회")
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