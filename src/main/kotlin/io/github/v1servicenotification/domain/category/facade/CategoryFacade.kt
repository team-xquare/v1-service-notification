package io.github.v1servicenotification.domain.category.facade

import io.github.v1servicenotification.domain.category.domain.NotificationCategory
import io.github.v1servicenotification.domain.category.domain.repository.NotificationCategoryRepository
import io.github.v1servicenotification.domain.category.exception.CategoryNotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
class CategoryFacade(
        private val notificationCategoryRepository: NotificationCategoryRepository
) {

    fun getCategoryById(uuid: UUID): NotificationCategory {
        return notificationCategoryRepository.findById(uuid)
                .orElseThrow {
                    CategoryNotFoundException.EXCEPTION
                }
    }

}