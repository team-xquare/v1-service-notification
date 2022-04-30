package io.github.v1servicenotification.setting.activeSetting.api

import java.util.*

interface ActivateNotificationCategory {
    fun activateNotificationCategory(categoryUUID: UUID): Int
}