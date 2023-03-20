package io.github.v1servicenotification.domain.detail.domain.repository.vo

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime
import java.util.UUID

class DetailVO @QueryProjection constructor(
    val id: UUID,

    val title: String,

    val content: String,

    val sentAt: LocalDateTime,

    isRead: Boolean,

    val userId: UUID,

    val name: String,

    val destination: String,

    val categoryImage: String,
) {
    var isRead: Boolean = isRead
        private set
}
