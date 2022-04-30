package io.github.v1servicenotification.domain.setting.domain.repository

import io.github.v1servicenotification.domain.setting.domain.SettingEntity
import io.github.v1servicenotification.domain.setting.domain.SettingId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NotificationSettingRepository : CrudRepository<SettingEntity, SettingId> {
    fun findBySettingId(settingId: SettingId): SettingEntity? //TODO settingId
}