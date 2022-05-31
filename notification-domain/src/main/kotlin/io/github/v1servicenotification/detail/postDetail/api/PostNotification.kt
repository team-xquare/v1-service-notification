package io.github.v1servicenotification.detail.postDetail.api

import java.util.*

interface PostNotification {
    fun postNotification(userId: UUID, categoryId: UUID, title: String, content: String)
}