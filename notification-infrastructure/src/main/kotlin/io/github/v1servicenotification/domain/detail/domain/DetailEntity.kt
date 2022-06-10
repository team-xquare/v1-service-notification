package io.github.v1servicenotification.domain.detail.domain

import io.github.v1servicenotification.domain.category.domain.CategoryEntity
import io.github.v1servicenotification.global.entity.BaseUUIDEntity
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
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

    @CreatedDate
    @field:NotNull
    var sentAt: LocalDateTime,

    @field:NotNull
    @field:Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    var isRead: Boolean,

    @field:NotNull
    val userId: UUID,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "notification_category_id")
    val categoryEntity: CategoryEntity

) : BaseUUIDEntity(id) {

    fun getCategoryId(): UUID {
        return categoryEntity.id!!
    }

    fun getCategoryName(): String {
        return categoryEntity.name
    }

    fun getCategoryDestination(): String {
        return categoryEntity.destination
    }

}