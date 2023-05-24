package io.github.v1servicenotification.setting.exception

import io.github.v1servicenotification.error.ErrorCode
import io.github.v1servicenotification.error.NotificationException

class SettingNotFoundException private constructor(): NotificationException(ErrorCode.CATEGORY_NOT_FOUND) {
    companion object {
        @JvmField
        val EXCEPTION = SettingNotFoundException()
    }
}
