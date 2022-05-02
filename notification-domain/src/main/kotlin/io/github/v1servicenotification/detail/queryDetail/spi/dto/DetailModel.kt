package io.github.v1servicenotification.detail.queryDetail.spi.dto

import java.time.LocalDateTime
import java.util.*

class DetailModel(
    val id: UUID,

    val title: String,

    val content: String,

    val sentAt: LocalDateTime,

    var isRead: Boolean,

    val userId: UUID,

    val name: String,

    val destination: String
)