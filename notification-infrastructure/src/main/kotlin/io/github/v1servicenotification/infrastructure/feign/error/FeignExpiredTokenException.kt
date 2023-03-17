package io.github.v1servicenotification.infrastructure.feign.error

import io.github.v1servicenotification.error.ErrorCode
import io.github.v1servicenotification.error.NotificationException

class FeignExpiredTokenException private constructor(): NotificationException(ErrorCode.FEIGN_EXPIRED_TOKEN) {
    companion object {
        @JvmField
        val EXCEPTION = FeignExpiredTokenException()
    }
}
