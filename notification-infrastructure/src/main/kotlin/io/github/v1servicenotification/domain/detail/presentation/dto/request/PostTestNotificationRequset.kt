package io.github.v1servicenotification.domain.detail.presentation.dto.request

import java.util.UUID
import javax.validation.constraints.NotNull

data class PostTestNotificationRequset(

    @field:NotNull
    val userId: UUID,

    @field:NotNull
    val topic: String,

    @field:NotNull
    val content: String,

    @field:NotNull
    val threadId: String,
)
