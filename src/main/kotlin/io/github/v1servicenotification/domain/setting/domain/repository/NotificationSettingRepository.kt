package io.github.v1servicenotification.domain.setting.domain.repository

import io.github.v1servicenotification.domain.setting.domain.NotificationSetting
import io.github.v1servicenotification.domain.setting.domain.SettingId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface NotificationSettingRepository : CrudRepository<NotificationSetting, SettingId> {
    fun findBySettingId(settingId: SettingId): NotificationSetting?
}