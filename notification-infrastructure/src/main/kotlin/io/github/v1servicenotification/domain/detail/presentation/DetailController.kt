package io.github.v1servicenotification.domain.detail.presentation

import io.github.v1servicenotification.detail.api.DetailApi
import io.github.v1servicenotification.detail.api.dto.response.DetailResponse
import io.github.v1servicenotification.detail.api.dto.response.NotificationCountResponse
import io.github.v1servicenotification.global.extension.getUserId
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "알림 상세", description = "알림 상세 Api입니다.")
@RestController
class DetailController(
    private val detailApi: DetailApi
) {

    @Operation(summary = "전송된 알림 목록")
    @GetMapping("/list")
    fun queryNotificationDetailList(): DetailResponse {
        return detailApi.queryNotificationDetail(getUserId())
    }

    @Operation(summary = "안읽은 알림 개수")
    @GetMapping("/unread")
    fun queryUnreadNotificationCount(): NotificationCountResponse {
        return detailApi.queryUnreadNotificationCount(getUserId())
    }
}
