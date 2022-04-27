package io.github.v1servicenotification.category

import io.github.v1servicenotification.annotation.Aggregate
import java.util.*

@Aggregate
class NotificationCategory(
    val id: UUID = UUID.randomUUID(),

    val name: String,

    val destination: String,

    val defaultActivated: Boolean
)