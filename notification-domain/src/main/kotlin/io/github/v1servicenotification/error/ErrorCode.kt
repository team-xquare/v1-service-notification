package io.github.v1servicenotification.error

enum class ErrorCode(
    val status: Int,
    val code: String,
    val message: String,
) {
    FEIGN_BAD_REQUEST(400, "FEIGN-400-1", "Feign Bad Request."),

    FEIGN_UNAUTHORIZED(401, "FEIGN-401-1", "Feign Unauthorized."),

    FEIGN_FORBIDDEN(403, "FEIGN-403-1", "Feign Forbidden."),

    FEIGN_EXPIRED_TOKEN(419, "FEIGN-419-1", "Feign Expired Token."),


    EMPTY_ARGUMENT(400, "NOTIFICATION-400-1", "Empty argument."),

    CATEGORY_NOT_FOUND(404, "CATEGORY-404-1", "Category Not Found."),

    DETAIL_NOT_FOUND(404, "DETAIL-404-1", "Detail Not Found."),

    INTERNAL_SERVER_ERROR(500, "GLOBAL-500-1", "Internal Server Error."),

    DEVICE_TOKEN_LENGTH(401, "DEVICE-400-1", "Device Token Length Error."),

    SETTING_NOT_FOUND(404, "SETTING-404-1", "Setting Not Found.")
}
