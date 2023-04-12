package io.github.v1servicenotification.domain.detail.presentation.dto.request

import java.util.UUID
import javax.validation.constraints.NotNull

data class PostTestNotificationRequset(

    @field:NotNull
    val userId: UUID,

    @field:NotNull
    val categoryId: UUID,

    @field:NotNull
    val title: String,

    @field:NotNull
    val content: String,
)
