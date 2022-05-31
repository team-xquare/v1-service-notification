package io.github.v1servicenotification.domain.notification.presentation.dto

import java.util.UUID
import javax.validation.constraints.NotNull

data class Group(
    @field:NotNull
    val categoryId: UUID?,

    @field:NotNull
    val title: String?,

    @field:NotNull
    val content: String?
) {
    constructor(): this(null, null, null)
}