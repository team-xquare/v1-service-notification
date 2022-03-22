package io.github.v1servicenotification.domain.setting.domain

import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull


@Table(name = "tbl_notification_setting")
@Entity
class NotificationSetting(
        @EmbeddedId
        val settingId: SettingId,

        @field:NotNull
        @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
        var isActivated: Boolean
)