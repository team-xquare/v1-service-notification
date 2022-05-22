package io.github.v1servicenotification.category.updateCategory.exception

import io.github.v1servicenotification.error.ErrorCode
import io.github.v1servicenotification.error.NotificationException

class CategoryNotFoundException private constructor(): NotificationException(ErrorCode.CATEGORY_NOT_FOUND) {
    companion object {
        val EXCEPTION = CategoryNotFoundException()
    }
}