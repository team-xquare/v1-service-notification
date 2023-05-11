package io.github.v1servicenotification.error

class InvalidDeviceTokenLengthException private constructor(): NotificationException(ErrorCode.DEVICE_TOKEN_LENGTH) {

    companion object {
        @JvmField
        val EXCEPTION = InvalidDeviceTokenLengthException()
    }
}
