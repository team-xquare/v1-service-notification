package io.github.v1servicenotification.error

abstract class NotificationException(
    val errorCode: ErrorCode
) : RuntimeException() {
    override fun fillInStackTrace(): Throwable {
        return this
    }
}