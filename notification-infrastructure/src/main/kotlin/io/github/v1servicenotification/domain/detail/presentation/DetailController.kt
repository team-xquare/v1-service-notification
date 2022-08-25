package io.github.v1servicenotification.domain.detail.presentation

import io.github.v1servicenotification.detail.api.DetailApi
import io.github.v1servicenotification.detail.api.dto.response.DetailResponse
import io.github.v1servicenotification.global.extension.getUserId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class DetailController(
    private val detailApi: DetailApi
) {

    @GetMapping("/")
    fun queryNotificationDetailList(): DetailResponse {
        return detailApi.queryNotificationDetail(getUserId())
    }

    @PostMapping("/{notification-uuid}")
    fun checkNotification(@PathVariable("notification-uuid") notificationId: UUID) {
        detailApi.checkNotification(getUserId(), notificationId)
    }

}