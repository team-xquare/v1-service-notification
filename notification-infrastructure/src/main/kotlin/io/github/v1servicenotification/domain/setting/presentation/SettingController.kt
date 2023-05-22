package io.github.v1servicenotification.domain.setting.presentation

import io.github.v1servicenotification.category.api.response.CategoryListResponse
import io.github.v1servicenotification.global.extension.getUserId
import io.github.v1servicenotification.setting.api.SettingApi
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestParam

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

    @Operation(summary = "알림 카테고리 활성화 / 비활성화")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    fun activateNotificationCategory(
        @RequestParam("is-activated") isActivate: Boolean,
        @RequestParam("topic") topic: String
    ) {
        settingApi.activateOrDeActivateCategory(isActivate, topic, getUserId())
    }
}
