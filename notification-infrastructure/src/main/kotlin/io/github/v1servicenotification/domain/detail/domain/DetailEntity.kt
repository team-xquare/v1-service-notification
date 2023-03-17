package io.github.v1servicenotification.domain.detail.domain

import io.github.v1servicenotification.domain.category.domain.CategoryEntity
import io.github.v1servicenotification.global.entity.BaseUUIDEntity
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Table(name = "tbl_notification_detail")
@EntityListeners(value = [AuditingEntityListener::class])
@Entity
class DetailEntity(
    override val id: UUID,

    @field:Size(max = 20)
    @field:NotNull
    val title: String,

    @field:NotNull
    val content: String,

    sentAt: LocalDateTime,

    isRead: Boolean,

    @field:Column(columnDefinition = "BINARY(16)")
    @field:NotNull
    val userId: UUID,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "notification_category_id")
    val categoryEntity: CategoryEntity

) : BaseUUIDEntity(id) {

    @CreatedDate
    @field:NotNull
    var sentAt: LocalDateTime = sentAt
        protected set

    @field:NotNull
    @field:Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    var isRead: Boolean = isRead
        protected set

    fun getCategoryId(): UUID {
        return categoryEntity.id
    }

    fun checkRead() {
        this.isRead = true
    }

}