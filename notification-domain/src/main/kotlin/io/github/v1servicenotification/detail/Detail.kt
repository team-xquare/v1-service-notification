package io.github.v1servicenotification.detail

import io.github.v1servicenotification.annotation.Aggregate
import java.time.LocalDateTime
import java.util.UUID

@Aggregate
class Detail(
    val id: UUID = UUID(0, 0),

    val title: String,

    val content: String,

    val sentAt: LocalDateTime,

    isRead: Boolean,

    val userId: UUID,

    val categoryId: UUID

) {
    var isRead: Boolean = isRead
        private set

    fun checkRead() {
        this.isRead = true;
    }
}
