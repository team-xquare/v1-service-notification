package io.github.v1servicenotification.domain.notification.presentation.dto

import java.util.*

data class Group(
    val categoryId: UUID,

    val title: String,

    val content: String,
)
