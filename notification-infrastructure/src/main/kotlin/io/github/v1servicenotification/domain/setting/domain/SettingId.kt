package io.github.v1servicenotification.domain.setting.domain

import io.github.v1servicenotification.domain.category.domain.CategoryEntity
import java.io.Serializable
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Embeddable
data class SettingId(

    @Column(columnDefinition = "BINARY(16)")
    val userId: UUID,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    val categoryEntity: CategoryEntity
) : Serializable
