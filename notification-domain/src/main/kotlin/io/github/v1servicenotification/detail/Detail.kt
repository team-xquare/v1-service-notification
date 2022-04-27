package io.github.v1servicenotification.detail

import io.github.v1servicenotification.annotation.Aggregate
import io.github.v1servicenotification.category.NotificationCategory
import java.time.LocalDateTime
import java.util.*

@Aggregate
class NotificationDetail(
    val title: String,

    val content: String,

    val sentAt: LocalDateTime,

    var isRead: Boolean,

    val userId: UUID,

    private val notificationCategory: NotificationCategory

) {

    fun getCategoryName(): String {
        return notificationCategory.name
    }

    fun getCategoryDestination(): String {
        return notificationCategory.destination
    }

}