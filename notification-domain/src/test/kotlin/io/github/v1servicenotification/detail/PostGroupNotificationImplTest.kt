package io.github.v1servicenotification.detail

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.updateCategory.exception.CategoryNotFoundException
import io.github.v1servicenotification.detail.postDetail.service.PostGroupNotificationImpl
import io.github.v1servicenotification.stubs.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class PostGroupNotificationImplTest {

    private val settingSpi = InMemorySettingRepository()
    private val detailSpi = InMemoryDetailRepository()
    private val categorySpi = InMemoryCategoryRepository()
    private val fcmSpi = InMemoryFcm()
    private val userSpi = InMemoryUser()
    private val postGroupNotification = PostGroupNotificationImpl(settingSpi, detailSpi, categorySpi, fcmSpi, userSpi)

    @Test
    fun postGroupNotification() {
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

        settingSpi.saveSetting(category, userId, true)

        detailSpi.save(category)

        postGroupNotification.postGroupNotification(category.id, title, content)

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
        val categoryId = UUID.randomUUID()
        val title = "Test Title"
        val content = "Test Content"

        assertThrows<CategoryNotFoundException> { postGroupNotification.postGroupNotification(categoryId, title, content) }
    }

}