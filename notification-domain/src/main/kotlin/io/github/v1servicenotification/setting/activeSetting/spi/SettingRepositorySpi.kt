package io.github.v1servicenotification.setting.activeSetting.spi

import io.github.v1servicenotification.annotation.Spi
import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.setting.Setting
import java.util.*

@Spi
interface SettingRepositorySpi {
    fun findBySettingId(userId: UUID, notificationCategory: Category): Setting?
}