package io.github.v1servicenotification.domain.category.service

import io.github.v1servicenotification.domain.category.domain.repository.NotificationCategoryRepository
import io.github.v1servicenotification.domain.category.presentation.dto.response.NotificationCategory
import io.github.v1servicenotification.domain.category.presentation.dto.response.NotificationCategoryListResponse
import org.springframework.stereotype.Service

@Service
class QueryActivatedCategory(
        private val notificationCategoryRepository: NotificationCategoryRepository
) {

    fun execute(): NotificationCategoryListResponse {
        return NotificationCategoryListResponse(
                notificationCategoryRepository.findByDefaultActivatedIsTrue()
                        .map {
                            NotificationCategory(
                                    id = it.id,
                                    name = it.name,
                                    destination = it.destination
                            )
                        }
        )
    }

}