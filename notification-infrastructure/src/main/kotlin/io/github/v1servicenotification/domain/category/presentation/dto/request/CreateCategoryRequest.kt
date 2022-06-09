package io.github.v1servicenotification.category.updateCategory.api.dto.request

class CreateCategoryRequest(
    val name: String,
    val destination: String,
    val defaultActivated: Boolean
)