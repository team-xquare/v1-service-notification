package io.github.v1servicenotification.domain.category.presentation.dto.response

import java.util.*

class NotificationCategory(
        val id: UUID,
        val name: String,
        val destination: String
)