package io.github.v1servicenotification.domain.detail.domain.repository.vo

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime
import java.util.*


class DetailVO @QueryProjection constructor(
    val id: UUID,

    val title: String,

    val content: String,

    val sentAt: LocalDateTime,

    var isRead: Boolean,

    val userId: UUID,

    val name: String,

    val destination: String
)