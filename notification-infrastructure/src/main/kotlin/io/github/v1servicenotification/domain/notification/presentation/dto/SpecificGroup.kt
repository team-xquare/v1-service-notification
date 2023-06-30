package io.github.v1servicenotification.domain.notification.presentation.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID


data class SpecificGroup(
    @JsonProperty("user_id_list")
    val userIdList: List<UUID>,

    val topic: String,

    val content: String,

    @JsonProperty("thread_id")
    val threadId: String
)