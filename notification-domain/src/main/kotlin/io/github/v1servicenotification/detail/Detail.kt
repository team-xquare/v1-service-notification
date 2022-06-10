package io.github.v1servicenotification.detail

import io.github.v1servicenotification.annotation.Aggregate
import java.time.LocalDateTime
import java.util.*

@Aggregate
class Detail(
    val id: UUID = UUID(0, 0),

    val title: String,

    val content: String,

    val sentAt: LocalDateTime,

    var isRead: Boolean,

    val userId: UUID,

    val categoryId: UUID

)