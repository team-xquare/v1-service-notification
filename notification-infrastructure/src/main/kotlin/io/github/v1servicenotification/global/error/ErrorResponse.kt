package io.github.v1servicenotification.global.error

import io.github.v1servicenotification.global.error.exception.ErrorCode

class ErrorResponse(
        errorCode: ErrorCode
) {

    private val status: Int = errorCode.status

    private val code: String = errorCode.code

    private val message: String = errorCode.message

    override fun toString(): String {
        return "{\n" +
                "\t\"status\": " + status +
                ",\t\"code\": \"" + code + '\"' +
                ",\n\t\"message\": \"" + message + '\"' +
                "\n}";
    }

}