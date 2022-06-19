package io.github.v1servicenotification.infrastructure.feign.error

import io.github.v1servicenotification.error.ErrorCode
import io.github.v1servicenotification.error.NotificationException

class OtherForbiddenException private constructor(): NotificationException(ErrorCode.OTHER_FORBIDDEN) {
    companion object {
        @JvmField
        val EXCEPTION = OtherForbiddenException()
    }
}