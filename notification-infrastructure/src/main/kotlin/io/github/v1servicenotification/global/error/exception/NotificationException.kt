package io.github.v1servicenotification.global.error.exception

open class NotificationException(
    val errorCode: ErrorCode
) : RuntimeException()