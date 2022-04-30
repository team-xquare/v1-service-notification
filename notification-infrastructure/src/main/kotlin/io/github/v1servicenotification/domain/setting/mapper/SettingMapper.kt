package io.github.v1servicenotification.domain.setting.mapper

import io.github.v1servicenotification.domain.setting.domain.SettingEntity
import io.github.v1servicenotification.setting.Setting
import org.mapstruct.Mapper

@Mapper
interface SettingMapper {
    fun settingDomainToEntity(setting: Setting): SettingEntity
    fun settingEntityToDomain(settingEntity: SettingEntity): Setting
}