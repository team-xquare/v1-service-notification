package io.github.v1servicenotification.category.updateCategory.api

import java.util.UUID

interface RemoveCategory {
    fun removeCategory(categoryId: UUID)
}