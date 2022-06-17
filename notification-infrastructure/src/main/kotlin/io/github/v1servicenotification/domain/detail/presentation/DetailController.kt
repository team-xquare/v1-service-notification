package io.github.v1servicenotification.domain.detail.presentation

import io.github.v1servicenotification.detail.postDetail.api.CheckNotification
import io.github.v1servicenotification.detail.queryDetail.api.QueryNotificationDetail
import io.github.v1servicenotification.detail.queryDetail.api.dto.response.DetailResponse
import io.github.v1servicenotification.global.extension.getUserId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class DetailController(
    private val queryNotificationDetail: QueryNotificationDetail,
    private val checkNotification: CheckNotification
) {

    @GetMapping("/")
    fun queryNotificationDetailList(): DetailResponse {
        return queryNotificationDetail.queryNotificationDetail(getUserId())
    }

    @PostMapping("/{notification-uuid}")
    fun checkNotification(@PathVariable("notification-uuid") notificationId: UUID) {
        checkNotification.checkNotification(getUserId(), notificationId)
    }

}