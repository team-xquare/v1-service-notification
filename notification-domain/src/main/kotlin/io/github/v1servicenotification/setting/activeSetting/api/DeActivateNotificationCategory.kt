package io.github.v1servicenotification.setting.activeSetting.api

import java.util.*

interface DeActivateNotificationCategory {
    fun deActivateNotificationCategory(categoryUUID: UUID): Int
}