package io.github.v1servicenotification.detail

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.exception.CategoryNotFoundException
import io.github.v1servicenotification.detail.postDetail.service.PostNotificationImpl
import io.github.v1servicenotification.stubs.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class PostNotificationImplTest {

    private val detailSpi = InMemoryDetailRepository()
    private val categorySpi = InMemoryCategoryRepository()
    private val fcmSpi = InMemoryFcm()
    private val userSpi = InMemoryUser()
    private val postNotification = PostNotificationImpl(detailSpi, fcmSpi, categorySpi, userSpi)

    @Test
    fun postNotification() {
        val userId = UUID.randomUUID()
        val title = "Test Title"
        val content = "Test Content"
        val destination = "/test"
        val categoryName = "Test Category"

        val category = Category(
            id = UUID.randomUUID(),
            name = categoryName,
            destination = destination,
            defaultActivated = true
        )

        categorySpi.saveCategory(category)
        detailSpi.save(category)

        postNotification.postNotification(userId, category.id, title, content)

        detailSpi.findAllByUserId(userId)
            .forEach {
                assertThat(it.title).isEqualTo(title)
                assertThat(it.content).isEqualTo(content)
                assertThat(it.destination).isEqualTo(destination)
                assertThat(it.userId).isEqualTo(userId)
                assertThat(it.name).isEqualTo(categoryName)
            }
    }

    @Test
    fun categoryNotFound() {
        val userId = UUID.randomUUID()
        val categoryId = UUID.randomUUID()
        val title = "Test Title"
        val content = "Test Content"

        assertThrows<CategoryNotFoundException> { postNotification.postNotification(categoryId, userId, title, content) }
    }

}