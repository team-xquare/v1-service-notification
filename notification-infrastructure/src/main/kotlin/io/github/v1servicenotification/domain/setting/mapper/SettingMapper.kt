package io.github.v1servicenotification.domain.setting.mapper

import io.github.v1servicenotification.domain.category.domain.CategoryEntity
import io.github.v1servicenotification.domain.setting.domain.SettingEntity
import io.github.v1servicenotification.domain.setting.domain.SettingId
import io.github.v1servicenotification.setting.Setting
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface SettingMapper {


    fun settingDomainToEntity(setting: Setting): SettingEntity

    @Mappings(
        Mapping(source = "settingId.userId", target = "userId"),
        Mapping(source = "settingId.categoryEntity.id", target = "notificationCategoryId"),
        Mapping(source = "activated", target = "isActivated")
    )
    fun settingEntityToDomain(settingEntity: SettingEntity): Setting
}