package io.github.v1servicenotification.category

import io.github.v1servicenotification.category.updateCategory.service.CreateCategoryImpl
import io.github.v1servicenotification.stubs.InMemoryCategoryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CreateCategoryImplTest {

    private val updateCategorySpi = InMemoryCategoryRepository()
    private val createCategory = CreateCategoryImpl(updateCategorySpi)

    @Test
    fun createDefaultActivatedTrueCategory() {
        val name = "Test name"
        val destination = "Test destination"
        val defaultActivated = true

        createCategory.createCategory(
            name = name,
            destination = destination,
            defaultActivated = defaultActivated
        )

        updateCategorySpi.findAllByDefaultActivatedIsTrue()
            .forEach {
                assertThat(it.name).isEqualTo(name)
                assertThat(it.destination).isEqualTo(destination)
                assertThat(it.defaultActivated).isEqualTo(defaultActivated)
            }

        assertThat(updateCategorySpi.findAllByDefaultActivatedIsTrue().size).isEqualTo(1)
    }

    @Test
    fun createDefaultActivatedFalseCategory() {
        val name = "Test name"
        val destination = "Test destination"
        val defaultActivated = false

        createCategory.createCategory(
            name = name,
            destination = destination,
            defaultActivated = defaultActivated
        )
        
        assertThat(updateCategorySpi.findAllByDefaultActivatedIsTrue().size).isEqualTo(0)
    }

}