package io.github.v1servicenotification.domain.category.domain.repository

import io.github.v1servicenotification.domain.category.domain.NotificationCategory
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface NotificationCategoryRepository : CrudRepository<NotificationCategory, UUID> {
}