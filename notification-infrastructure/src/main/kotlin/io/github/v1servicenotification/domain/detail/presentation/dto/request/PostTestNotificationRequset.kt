package io.github.v1servicenotification.domain.detail.presentation.dto.request

import java.util.UUID
import javax.validation.constraints.NotBlank

data class PostTestNotificationRequset(

    @field:NotBlank
    val userId: UUID,

    @field:NotBlank
    val topic: String,

    @field:NotBlank
    val content: String,
)
