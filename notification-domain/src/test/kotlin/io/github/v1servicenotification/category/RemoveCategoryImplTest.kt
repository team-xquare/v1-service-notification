package io.github.v1servicenotification.category

import io.github.v1servicenotification.category.updateCategory.service.RemoveCategoryImpl
import io.github.v1servicenotification.stubs.InMemoryCategoryRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class RemoveCategoryImplTest {

    private val updateCategorySpi = InMemoryCategoryRepository()
    private val removeCategory = RemoveCategoryImpl(updateCategorySpi)

    @Test
    fun remove() {
        val id = UUID.randomUUID()
        val name = "Test name"
        val destination = "Test destination"
        val defaultActivated = false

        updateCategorySpi.saveCategory(
            Category(
                id = id,
                name = name,
                destination = destination,
                defaultActivated = defaultActivated
            )
        )

        removeCategory.removeCategory(id)
    }

    @Test
    fun removeCategoryNotFound() {
        assertThrows<NullPointerException> { removeCategory.removeCategory(UUID.randomUUID()) }
    }

}