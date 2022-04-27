package io.github.v1servicenotification.setting.activeSetting.api

import java.util.*

interface DeActivateNotificationCategory {
    suspend fun deActivateNotificationCategory(categoryUUID: UUID): Int
}