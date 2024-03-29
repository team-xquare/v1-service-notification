package io.github.v1servicenotification.domain.category.domain

import io.github.v1servicenotification.domain.setting.domain.SettingEntity
import io.github.v1servicenotification.global.entity.BaseUUIDEntity
import java.util.UUID
import javax.persistence.Table
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Table(name = "tbl_notification_category")
@Entity
class CategoryEntity(
    override val id: UUID,

    @field:Size(max = 20)
    @field:NotNull
    val title: String,

    @field:NotNull
    val destination: String,

    @field:NotNull
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    val defaultActivated: Boolean,

    @OneToMany(mappedBy = "settingId.categoryEntity", fetch = FetchType.LAZY)
    val settingList: List<SettingEntity> = emptyList(),

    @Column(unique = true, nullable = false)
    val topic: String,
) : BaseUUIDEntity(id)
