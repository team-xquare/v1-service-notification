package io.github.v1servicenotification.category

import io.github.v1servicenotification.category.exception.CategoryNotFoundException
import io.github.v1servicenotification.category.service.CategoryApiImpl
import io.github.v1servicenotification.stubs.InMemoryCategoryRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class CategoryImplTest {

    private val queryCategorySpi = InMemoryCategoryRepository()
    private val updateCategorySpi = InMemoryCategoryRepository()

    private val category = CategoryApiImpl(queryCategorySpi, updateCategorySpi)

    @Test
    fun createDefaultActivatedTrueCategory() {
        val name = "Test name"
        val destination = "Test destination"
        val defaultActivated = true

        category.createCategory(
            title = name,
            destination = destination,
            defaultActivated = defaultActivated,
            topic = "ALL"
        )

        updateCategorySpi.findAllByDefaultActivated(true)
            .forEach {
                Assertions.assertThat(it.title).isEqualTo(name)
                Assertions.assertThat(it.destination).isEqualTo(destination)
                Assertions.assertThat(it.defaultActivated).isEqualTo(defaultActivated)
            }

        Assertions.assertThat(updateCategorySpi.findAllByDefaultActivated(true).size).isEqualTo(1)
    }

    @Test
    fun createDefaultActivatedFalseCategory() {
        val name = "Test name"
        val destination = "Test destination"
        val defaultActivated = false

        category.createCategory(
            title = name,
            destination = destination,
            defaultActivated = defaultActivated,
            topic = "ALL"
        )

        Assertions.assertThat(updateCategorySpi.findAllByDefaultActivated(true).size).isEqualTo(0)
    }

    @Test
    fun 기본_카테고리_존재() {
        queryCategorySpi.saveCategory(
            Category(
                UUID.randomUUID(),
                "Test category",
                "Test destination",
                true,
                topic = "ALL"
            )
        )
        assertThat(category.queryNotificationCategory(true).categories.size)
            .isEqualTo(1)
    }

    @Test
    fun 기본_카테고리_미존재() {
        queryCategorySpi.saveCategory(
            Category(
                UUID.randomUUID(),
                "Test category",
                "Test destination",
                false,
                topic = "ALL"
            )
        )

        assertThat(category.queryNotificationCategory(true).categories.size)
            .isEqualTo(0)
    }

    @Test
    fun 비기본_카테고리_존재() {
        queryCategorySpi.saveCategory(
            Category(
                UUID.randomUUID(),
                "Test category",
                "Test destination",
                false,
                topic = "ALL"
            )
        )
        assertThat(category.queryNotificationCategory(false).categories.size)
            .isEqualTo(1)
    }

    @Test
    fun 비기본_카테고리_미존재() {
        queryCategorySpi.saveCategory(
            Category(
                UUID.randomUUID(),
                "Test category",
                "Test destination",
                true,
                topic = "ALL"
            )
        )

        assertThat(category.queryNotificationCategory(false).categories.size)
            .isEqualTo(0)
    }

    @Test
    fun remove() {
        val id = UUID.randomUUID()
        val name = "Test name"
        val destination = "Test destination"
        val defaultActivated = false

        updateCategorySpi.saveCategory(
            Category(
                id = id,
                title = name,
                destination = destination,
                defaultActivated = defaultActivated,
                topic = "ALL"
            )
        )

        category.removeCategory(id)
    }

    @Test
    fun removeCategoryNotFound() {
        assertThrows<CategoryNotFoundException> { category.removeCategory(UUID.randomUUID()) }
    }
}
