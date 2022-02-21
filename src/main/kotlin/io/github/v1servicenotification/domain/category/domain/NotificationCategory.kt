package io.github.v1servicenotification.domain.category.domain

import io.github.v1servicenotification.global.entity.BaseUUIDEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Table(name = "tbl_ notification_category")
@Entity
class NotificationCategory(
        @field:Size(max = 20)
        @field:NotNull
        val name: String,

        @field:NotNull
        val destination: String,

        @Column(columnDefinition = "TINYINT(1)")
        @field:NotNull
        val defaultActivated: Boolean
) : BaseUUIDEntity()