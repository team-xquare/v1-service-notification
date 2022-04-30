package io.github.v1servicenotification.domain.detail.presentation

import io.github.v1servicenotification.domain.detail.presentation.dto.response.NotificationDetailListResponse
import io.github.v1servicenotification.domain.detail.service.QueryNotificationDetailListService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DetailController(
    private val queryNotificationDetailListService: QueryNotificationDetailListService
) {

    @GetMapping("/")
    fun queryNotificationDetailList(): NotificationDetailListResponse {
        return queryNotificationDetailListService.execute()
    }

}