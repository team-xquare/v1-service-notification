package io.github.v1servicenotification.domain.category.domain

import io.github.v1servicenotification.global.entity.BaseUUIDEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Table(name = "tbl_notification_category")
@Entity
class CategoryEntity(
    override val id: UUID,

    @field:Size(max = 20)
    @field:NotNull
    val name: String,

    @field:NotNull
    val destination: String,

    @field:NotNull
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    val defaultActivated: Boolean
) : BaseUUIDEntity(id)