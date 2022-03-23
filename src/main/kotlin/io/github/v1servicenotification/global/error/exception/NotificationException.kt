package io.github.v1servicenotification.global.error.exception

class NotificationException(
        val errorCode: ErrorCode
): RuntimeException()