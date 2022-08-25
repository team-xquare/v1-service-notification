package io.github.v1servicenotification.detail

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.exception.CategoryNotFoundException
import io.github.v1servicenotification.detail.exception.NotificationDetailNotFoundException
import io.github.v1servicenotification.detail.service.DetailApiImpl
import io.github.v1servicenotification.stubs.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*
import org.assertj.core.api.Assertions.*
import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.assertThrows

class DetailApiImplTest {

    private val detailSpi = InMemoryDetailRepository()
    private val settingSpi = InMemorySettingRepository()
    private val categorySpi = InMemoryCategoryRepository()
    private val fcmSpi = InMemoryFcm()
    private val userSpi = InMemoryUser()
    private val detailApi = DetailApiImpl(settingSpi, detailSpi, categorySpi, fcmSpi, userSpi, detailSpi)

    @Test
    fun checkNotification() {
        val userId = UUID.randomUUID()
        val detailId = UUID.randomUUID()

        val detail = Detail(
            id = detailId,
            title = "Test title",
            content = "Test Content",
            sentAt = LocalDateTime.now(),
            isRead = false,
            userId = userId,
            categoryId = UUID.randomUUID()
        )

        detailSpi.save(detail)

        detailApi.checkNotification(userId, detailId)

        AssertionsForClassTypes.assertThat(detail.isRead).isEqualTo(true)
    }

    @Test
    fun detailNotFound() {
        val userId = UUID.randomUUID()
        val detailId = UUID.randomUUID()

        assertThrows<NotificationDetailNotFoundException> {
            detailApi.checkNotification(userId, detailId)
        }
    }

    @Test
    fun detailNotFoundUser() {
        val userId = UUID.randomUUID()
        val detailId = UUID.randomUUID()

        detailSpi.save(
            Detail(
                id = detailId,
                title = "Test title",
                content = "Test Content",
                sentAt = LocalDateTime.now(),
                isRead = false,
                userId = UUID.randomUUID(),
                categoryId = UUID.randomUUID()
            )
        )

        assertThrows<NotificationDetailNotFoundException> {
            detailApi.checkNotification(userId, detailId)
        }
    }

    @Test
    fun queryNotificationDetail() {
        val category = Category(
            UUID.randomUUID(),
            "Test category",
            "Test destination",
            true
        )
        val userId = UUID.randomUUID()

        detailSpi.save(category)
        detailSpi.save(
            Detail(UUID.randomUUID(),
                "Test title",
                "Test content",
                LocalDateTime.now(),
                true,
                userId,
                category.id
            )
        )

        assertThat(detailApi.queryNotificationDetail(userId).notifications[0].userId)
            .isEqualTo(userId)
    }

    @Test
    fun 기본_알림_true_카테고리_전송() {
        val userId = UUID.randomUUID()
        userSpi.setUserId(userId)
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

        detailApi.postGroupNotification(category.id, title, content)

        assertThat(detailSpi.findAllByUserId(userId).size).isEqualTo(1)

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
    fun 기본_알림_true_카테고리_비활성_전송() {
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

        settingSpi.saveSetting(category, userId, false)

        detailSpi.save(category)

        detailApi.postGroupNotification(category.id, title, content)

        detailSpi.findAllByUserId(userId)
            .forEach {
                assertThat(it.title).isEqualTo(title)
                assertThat(it.content).isEqualTo(content)
                assertThat(it.destination).isEqualTo(destination)
                assertThat(it.userId).isNotEqualTo(userId)
                assertThat(it.name).isEqualTo(categoryName)
            }
    }

    @Test
    fun 기본_알림_false_카테고리_전송() {
        val userId = UUID.randomUUID()
        userSpi.setUserId(userId)
        val title = "Test Title"
        val content = "Test Content"
        val destination = "/test"
        val categoryName = "Test Category"

        val category = Category(
            id = UUID.randomUUID(),
            name = categoryName,
            destination = destination,
            defaultActivated = false
        )

        categorySpi.saveCategory(category)

        settingSpi.saveSetting(category, userId, true)

        detailSpi.save(category)

        detailApi.postGroupNotification(category.id, title, content)

        assertThat(detailSpi.findAllByUserId(userId).size).isEqualTo(1)

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
    fun groupCategoryNotFound() {
        val categoryId = UUID.randomUUID()
        val title = "Test Title"
        val content = "Test Content"

        assertThrows<CategoryNotFoundException> { detailApi.postGroupNotification(categoryId, title, content) }
    }

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

        detailApi.postNotification(userId, category.id, title, content)

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
    fun singleCategoryNotFound() {
        val userId = UUID.randomUUID()
        val categoryId = UUID.randomUUID()
        val title = "Test Title"
        val content = "Test Content"

        assertThrows<CategoryNotFoundException> { detailApi.postNotification(categoryId, userId, title, content) }
    }

}