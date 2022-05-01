package io.github.v1servicenotification.global.error.exception

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCode(
    val status: Int,
    val code: String,
    val message: String
) {

    INTERNAL_SERVER_ERROR(500, "GLOBAL-500-1", "Internal Server Error."),

    CATEGORY_NOT_FOUND(404, "CATEGORY-404-1", "Category Not Found.")

}