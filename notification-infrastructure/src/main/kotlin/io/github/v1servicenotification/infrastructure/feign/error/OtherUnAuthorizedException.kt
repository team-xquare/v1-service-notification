package io.github.v1servicenotification.infrastructure.feign.error

import io.github.v1servicenotification.error.ErrorCode
import io.github.v1servicenotification.error.NotificationException

class OtherUnAuthorizedException private constructor(): NotificationException(ErrorCode.OTHER_UNAUTHORIZED) {
    companion object {
        @JvmField
        val EXCEPTION = OtherUnAuthorizedException()
    }
}