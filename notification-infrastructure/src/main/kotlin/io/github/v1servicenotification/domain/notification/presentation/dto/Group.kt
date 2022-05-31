package io.github.v1servicenotification.domain.notification.presentation.dto

import java.util.UUID

class Group(
    val categoryId: UUID? = null,
    val title: String? = null,
    val content: String? = null
)