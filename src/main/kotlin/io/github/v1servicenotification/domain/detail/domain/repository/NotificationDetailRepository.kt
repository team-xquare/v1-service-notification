package io.github.v1servicenotification.domain.detail.domain.repository

import io.github.v1servicenotification.domain.detail.domain.NotificationDetail
import org.springframework.data.repository.CrudRepository
import java.util.*

interface NotificationDetailRepository : CrudRepository<NotificationDetail, UUID> {
}