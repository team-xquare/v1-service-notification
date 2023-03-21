package io.github.v1servicenotification.detail.spi.dto

import io.github.v1servicenotification.category.Topic
import java.time.LocalDateTime
import java.util.UUID

class TopicDetailModel(
    val id: UUID,

    val title: String,

    val content: String,

    val sentAt: LocalDateTime,

    isRead: Boolean,

    val userId: UUID,

    val name: String,

    val destination: String,

    val topic: Topic,
) {
    var isRead: Boolean = isRead
        private set
}
