package io.github.v1servicenotification.domain.notification.presentation.dto

import java.util.UUID

data class Personal(
    val categoryId: UUID,

    val userId: UUID,

    val title: String,

    val content: String,
)
