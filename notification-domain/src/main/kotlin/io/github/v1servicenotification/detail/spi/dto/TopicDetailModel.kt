package io.github.v1servicenotification.detail.spi.dto

import java.time.LocalDateTime
import java.util.UUID

class TopicDetailModel(
    val id: UUID,

    val title: String,

    val content: String,

    val sentAt: LocalDateTime,

    isRead: Boolean,

    val userId: UUID,

    val topic: String,
) {
    var isRead: Boolean = isRead
        private set
}
