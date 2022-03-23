package io.github.v1servicenotification.domain.category.exception

import io.github.v1servicenotification.global.error.exception.ErrorCode
import io.github.v1servicenotification.global.error.exception.NotificationException

class CategoryNotFoundException private constructor() : NotificationException(ErrorCode.CATEGORY_NOT_FOUND) {

    companion object {
        val EXCEPTION: NotificationException = CategoryNotFoundException()
    }

}