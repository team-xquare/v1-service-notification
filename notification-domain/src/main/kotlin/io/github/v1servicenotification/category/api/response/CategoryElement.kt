package io.github.v1servicenotification.category.api.response

import java.util.UUID

class CategoryElement(
    val id: UUID,
    val title: String,
    val destination: String,
    val topic: String,
)
