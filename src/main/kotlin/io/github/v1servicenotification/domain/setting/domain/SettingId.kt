package io.github.v1servicenotification.domain.setting.domain

import io.github.v1servicenotification.domain.category.domain.NotificationCategory
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Embeddable
data class SettingId(

        @Column(columnDefinition = "BINARY(16)")
        val userId: UUID,

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "category_id")
        val notificationCategory: NotificationCategory
) : Serializable