package io.github.v1servicenotification.error

enum class ErrorCode(
    val status: Int,
    val code: String,
    val message: String
) {
    CATEGORY_NOT_FOUND(404, "CATEGORY-404-1", "Category Not Found."),

    INTERNAL_SERVER_ERROR(500, "GLOBAL-500-1", "Internal Server Error.")
}