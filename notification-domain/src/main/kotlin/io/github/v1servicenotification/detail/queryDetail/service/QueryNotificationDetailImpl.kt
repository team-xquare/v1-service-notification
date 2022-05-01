package io.github.v1servicenotification.detail.queryDetail.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.queryCategory.spi.CategoryRepositorySpi
import io.github.v1servicenotification.detail.queryDetail.api.QueryNotificationDetail
import io.github.v1servicenotification.detail.queryDetail.api.dto.response.DetailElement
import io.github.v1servicenotification.detail.queryDetail.api.dto.response.DetailResponse
import io.github.v1servicenotification.detail.queryDetail.spi.DetailRepositorySpi
import java.util.*

@DomainService
class QueryNotificationDetailImpl(
    private val detailRepositorySpi: DetailRepositorySpi,
    private val categorySpi: CategoryRepositorySpi
) : QueryNotificationDetail {

    override fun queryNotificationDetail(userId: UUID): DetailResponse {
        return DetailResponse(
            detailRepositorySpi.findAllByUserId(userId)
                .map {
                    val category = categorySpi.findById(it.notificationCategoryId)
                    DetailElement(
                        id = it.id,
                        title = it.title,
                        content = it.content,
                        sentAt = it.sentAt,
                        isRead = it.isRead,
                        userId = it.userId,
                        name = category.name,
                        destination = category.destination
                    )
                }
                .toList()
        )
    }


}