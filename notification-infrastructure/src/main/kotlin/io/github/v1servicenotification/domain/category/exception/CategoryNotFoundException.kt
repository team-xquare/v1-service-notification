package io.github.v1servicenotification.domain.category.exception

import io.github.v1servicenotification.error.ErrorCode
import io.github.v1servicenotification.error.NotificationException

class CategoryNotFoundException private constructor() : NotificationException(ErrorCode.CATEGORY_NOT_FOUND) {

    companion object {
        @JvmField
        val EXCEPTION: NotificationException = CategoryNotFoundException()
    }
}
