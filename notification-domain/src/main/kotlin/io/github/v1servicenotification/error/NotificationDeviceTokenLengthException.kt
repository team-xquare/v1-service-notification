package io.github.v1servicenotification.error

class NotificationDeviceTokenLengthException private constructor(): NotificationException(ErrorCode.DETAIL_NOT_FOUND) {

    companion object {
        @JvmField
        val EXCEPTION = NotificationDeviceTokenLengthException()
    }
}
