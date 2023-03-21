package io.github.v1servicenotification.category.api.response

import io.github.v1servicenotification.category.Topic
import java.util.UUID

class CategoryElement(
    val id: UUID,
    val name: String,
    val destination: String,
    val topic: Topic,
)
