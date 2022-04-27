package io.github.v1servicenotification.setting.activeSetting.spi

import io.github.v1servicenotification.annotation.Spi
import io.github.v1servicenotification.category.NotificationCategory
import io.github.v1servicenotification.setting.NotificationSetting
import java.util.UUID

@Spi
interface SettingRepositorySpi {
    suspend fun findBySettingId(userId: UUID, notificationCategory: NotificationCategory): NotificationSetting?
}