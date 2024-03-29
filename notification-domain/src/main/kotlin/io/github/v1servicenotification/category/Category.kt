package io.github.v1servicenotification.category

import io.github.v1servicenotification.annotation.Aggregate
import java.util.UUID

@Aggregate
class Category(
    val id: UUID = UUID(0, 0),

    val title: String,

    val destination: String,

    val defaultActivated: Boolean,

    val topic: String,
)
