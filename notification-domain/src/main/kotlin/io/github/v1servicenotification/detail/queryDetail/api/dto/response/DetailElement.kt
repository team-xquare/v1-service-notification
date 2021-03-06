package io.github.v1servicenotification.detail.queryDetail.api.dto.response

import java.time.LocalDateTime
import java.util.*

class DetailElement(
    val id: UUID,
    val title: String,
    val content: String,
    val sentAt: LocalDateTime,
    val isRead: Boolean,
    val userId: UUID,
    val name: String,
    val destination: String
)