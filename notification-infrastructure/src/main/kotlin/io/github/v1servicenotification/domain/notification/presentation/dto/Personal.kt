package io.github.v1servicenotification.domain.notification.presentation.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class Personal(

    @JsonProperty("user_id")
    val userId: UUID,

    @JsonProperty("category_id")
    val categoryId: UUID,

    val title: String,

    val content: String,
)
