package io.github.v1servicenotification.domain.notification.presentation.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class Group(
    @JsonProperty("category_id")
    val categoryId: UUID,

    val title: String,

    val content: String,
)
