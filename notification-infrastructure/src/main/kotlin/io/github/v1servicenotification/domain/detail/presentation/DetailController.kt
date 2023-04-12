package io.github.v1servicenotification.domain.detail.presentation

import io.github.v1servicenotification.detail.api.DetailApi
import io.github.v1servicenotification.detail.api.dto.response.DetailResponse
import io.github.v1servicenotification.detail.api.dto.response.NotificationCountResponse
import io.github.v1servicenotification.domain.detail.presentation.dto.request.PostTestNotificationRequset
import io.github.v1servicenotification.global.extension.getUserId
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

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

    @Operation(summary = "테스트 알림 전송")
    @PostMapping("/")
    fun postNotification(@RequestBody @Valid request: PostTestNotificationRequset) {
        detailApi.postNotification(request.userId, request.categoryId, request.title, request.content)
    }
}
