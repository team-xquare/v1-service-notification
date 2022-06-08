package io.github.v1servicenotification.domain.setting.domain

import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull


@Table(name = "tbl_notification_setting")
@Entity
class SettingEntity(
    settingId: SettingId,
    isActivated: Boolean
) {

    @EmbeddedId
    val settingId: SettingId = settingId


    @field:NotNull
    @Column(columnDefinition = "BIT")
    var isActivated: Boolean = isActivated
        protected set

    fun changeIsActivate(isActivated: Boolean) {
        this.isActivated = isActivated;
    }

}