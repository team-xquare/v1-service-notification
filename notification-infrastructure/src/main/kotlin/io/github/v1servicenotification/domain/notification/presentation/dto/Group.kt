package io.github.v1servicenotification.domain.notification.presentation.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Group(
    val topic: String,

    val content: String,

    @JsonProperty("thread-id")
    val threadId: String,
)
