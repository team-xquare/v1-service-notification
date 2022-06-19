package io.github.v1servicenotification.infrastructure.feign.error

import io.github.v1servicenotification.error.ErrorCode
import io.github.v1servicenotification.error.NotificationException

class FeignBadRequestException private constructor(): NotificationException(ErrorCode.FEIGN_BAD_REQUEST) {
    companion object {
        @JvmField
        val EXCEPTION = FeignBadRequestException()
    }
}