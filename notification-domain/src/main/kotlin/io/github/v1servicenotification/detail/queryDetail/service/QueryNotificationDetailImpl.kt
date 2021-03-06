package io.github.v1servicenotification.detail.queryDetail.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.detail.queryDetail.api.QueryNotificationDetail
import io.github.v1servicenotification.detail.queryDetail.api.dto.response.DetailElement
import io.github.v1servicenotification.detail.queryDetail.api.dto.response.DetailResponse
import io.github.v1servicenotification.detail.queryDetail.spi.QueryDetailRepositorySpi
import java.util.*

@DomainService
class QueryNotificationDetailImpl(
    private val queryDetailRepositorySpi: QueryDetailRepositorySpi,
) : QueryNotificationDetail {

    override fun queryNotificationDetail(userId: UUID): DetailResponse {
        return DetailResponse(
            queryDetailRepositorySpi.findAllByUserId(userId)
                .map {
                    DetailElement(
                        id = it.id,
                        title = it.title,
                        content = it.content,
                        sentAt = it.sentAt,
                        isRead = it.isRead,
                        userId = it.userId,
                        name = it.name,
                        destination = it.destination
                    )
                }
                .toList()
        )
    }


}