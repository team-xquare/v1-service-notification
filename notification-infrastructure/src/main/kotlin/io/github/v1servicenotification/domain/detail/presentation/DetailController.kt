package io.github.v1servicenotification.domain.detail.presentation

import io.github.v1servicenotification.detail.queryDetail.api.QueryNotificationDetail
import io.github.v1servicenotification.detail.queryDetail.api.dto.response.DetailResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class DetailController(
    private val queryNotificationDetail: QueryNotificationDetail
) {

    @GetMapping("/")
    fun queryNotificationDetailList(): DetailResponse {
        return queryNotificationDetail.queryNotificationDetail(UUID.randomUUID()) //TODO User uuid
    }

}