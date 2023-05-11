package io.github.v1servicenotification.error

class NotificationDeviceTokenLengthException private constructor(): NotificationException(ErrorCode.DEVICE_TOKEN_LENGTH) {

    companion object {
        @JvmField
        val EXCEPTION = NotificationDeviceTokenLengthException()
    }
}
