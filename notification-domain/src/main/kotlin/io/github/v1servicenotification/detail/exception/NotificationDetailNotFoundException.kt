package io.github.v1servicenotification.detail.exception

import io.github.v1servicenotification.error.ErrorCode
import io.github.v1servicenotification.error.NotificationException

class NotificationDetailNotFoundException private constructor(): NotificationException(ErrorCode.DETAIL_NOT_FOUND) {
    companion object {
        @JvmField
        val EXCEPTION = NotificationDetailNotFoundException()
    }
}
