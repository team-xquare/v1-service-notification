package io.github.v1servicenotification.category.updateCategory.api

interface CreateCategory {
    fun createCategory(name: String, destination: String, defaultActivated: Boolean)
}