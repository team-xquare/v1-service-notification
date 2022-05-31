package io.github.v1servicenotification.domain.notification.exception

import io.github.v1servicenotification.error.ErrorCode
import io.github.v1servicenotification.error.NotificationException

class EmptyArgumentException private constructor(): NotificationException(ErrorCode.EMPTY_ARGUMENT) {

    companion object {
        @JvmField
        val EXCEPTION = EmptyArgumentException()
    }
}