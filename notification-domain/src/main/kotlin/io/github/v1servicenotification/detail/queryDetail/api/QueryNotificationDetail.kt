package io.github.v1servicenotification.detail.queryDetail.api

import io.github.v1servicenotification.detail.queryDetail.api.dto.response.DetailResponse
import java.util.UUID

interface QueryNotificationDetail {
    fun queryNotificationDetail(userId: UUID): DetailResponse
}