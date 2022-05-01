package io.github.v1servicenotification.domain.detail.service

import io.github.v1servicenotification.domain.detail.domain.repository.NotificationDetailRepository
import io.github.v1servicenotification.domain.detail.presentation.dto.response.NotificationDetailListResponse
import io.github.v1servicenotification.domain.detail.presentation.dto.response.NotificationDetailResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class QueryNotificationDetailListService(
    private val notificationDetailRepository: NotificationDetailRepository
) {

    fun execute(): NotificationDetailListResponse {
        return NotificationDetailListResponse(
            notificationDetailRepository.findAllByUserId(UUID.randomUUID()) //TODO userId
                .map {
                    NotificationDetailResponse(
                        id = it.id,
                        title = it.title,
                        content = it.content,
                        sentAt = it.sentAt,
                        isRead = it.isRead,
                        userId = it.userId,
                        name = it.getCategoryName(),
                        destination = it.getCategoryDestination()
                    )
                }.toList()
        )
    }

}