package io.github.v1servicenotification.detail.postDetail.api

import java.util.UUID

interface PostGroupNotification {
    fun postGroupNotification(categoryId: UUID, title: String, content: String)
}