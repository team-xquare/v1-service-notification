package io.github.v1servicenotification.setting.activeSetting.api

import java.util.*

interface ActivateNotificationCategory {
    suspend fun activateNotificationCategory(categoryUUID: UUID): Int
}