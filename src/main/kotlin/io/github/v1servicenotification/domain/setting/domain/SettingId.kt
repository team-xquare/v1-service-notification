package io.github.v1servicenotification.domain.setting.domain

import io.github.v1servicenotification.domain.category.domain.NotificationCategory
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Embeddable
class SettingId : Serializable {

    @Column(columnDefinition = "BINARY(16)")
    private var user_id: UUID? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private var document: NotificationCategory? = null

}