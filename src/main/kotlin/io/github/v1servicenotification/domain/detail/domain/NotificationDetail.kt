package io.github.v1servicenotification.domain.detail.domain

import io.github.v1servicenotification.domain.category.domain.NotificationCategory
import io.github.v1servicenotification.global.entity.BaseUUIDEntity
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@EntityListeners(value =  [AuditingEntityListener::class])
class NotificationDetail(
        @field:Size(max = 20)
        @field:NotNull()
        val title: String,

        @field:NotNull()
        val content: String,

        @CreatedDate
        @field:NotNull()
        val sentAt: String,

        @field:NotNull()
        var isRead: Boolean,

        @field:NotNull()
        val userId: UUID,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "notification_category_id")
        val notificationCategory: NotificationCategory

) : BaseUUIDEntity()