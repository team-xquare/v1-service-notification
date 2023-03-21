package io.github.v1servicenotification.detail.api.dto.response

import io.github.v1servicenotification.category.Topic
import java.time.LocalDateTime
import java.util.UUID

class DetailElement(
    val id: UUID,
    val title: String,
    val content: String,
    val sentAt: LocalDateTime,
    val isRead: Boolean,
    val userId: UUID,
    val categoryName: String,
    val destination: String,
    val topic: Topic,
)
