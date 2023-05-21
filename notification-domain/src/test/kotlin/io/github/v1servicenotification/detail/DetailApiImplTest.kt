package io.github.v1servicenotification.detail

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.exception.CategoryNotFoundException
import io.github.v1servicenotification.detail.service.NotificationDetailApiImpl
import io.github.v1servicenotification.stubs.InMemoryCategoryRepository
import io.github.v1servicenotification.stubs.InMemoryDetailRepository
import io.github.v1servicenotification.stubs.InMemoryFcm
import io.github.v1servicenotification.stubs.InMemorySettingRepository
import io.github.v1servicenotification.stubs.InMemoryUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.util.UUID

class DetailApiImplTest {

    private val detailSpi = InMemoryDetailRepository()
    private val settingSpi = InMemorySettingRepository()
    private val categorySpi = InMemoryCategoryRepository()
    private val fcmSpi = InMemoryFcm()
    private val userSpi = InMemoryUser()
    private val detailApi = NotificationDetailApiImpl(settingSpi, detailSpi, categorySpi, fcmSpi, userSpi, detailSpi)

    @Test
    fun queryNotificationDetail() {
        val category = Category(
            UUID.randomUUID(),
            "Test category",
            "Test destination",
            true,
            topic = "ALL"
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
        val threadId = "threadId"

        val category = Category(
            id = UUID.randomUUID(),
            title = title,
            destination = destination,
            defaultActivated = true,
            topic = "ALL"
        )

        categorySpi.saveCategory(category)

        settingSpi.saveAllSetting(category, userId, true)

        detailSpi.save(category)

        detailApi.postGroupNotification(category.topic, content, threadId)

        assertThat(detailSpi.findAllByUserIdOrderBySentAtDesc(userId).size).isEqualTo(1)

        detailSpi.findAllByUserIdOrderBySentAtDesc(userId)
            .forEach {
                assertThat(it.title).isEqualTo(title)
                assertThat(it.content).isEqualTo(content)
                assertThat(it.userId).isEqualTo(userId)
            }

    }

    @Test
    fun 기본_알림_true_카테고리_비활성_전송() {
        val userId = UUID.randomUUID()
        val title = "Test Title"
        val content = "Test Content"
        val destination = "/test"
        val threadId = "threadId"

        val category = Category(
            id = UUID.randomUUID(),
            title = title,
            destination = destination,
            defaultActivated = true,
            topic = "ALL"
        )

        categorySpi.saveCategory(category)

        settingSpi.saveAllSetting(category, userId, false)

        detailSpi.save(category)

        detailApi.postGroupNotification(category.topic, content, threadId)

        detailSpi.findAllByUserIdOrderBySentAtDesc(userId)
            .forEach {
                assertThat(it.title).isEqualTo(title)
                assertThat(it.content).isEqualTo(content)
                assertThat(it.userId).isNotEqualTo(userId)
            }
    }

    @Test
    fun 기본_알림_false_카테고리_전송() {
        val userId = UUID.randomUUID()
        userSpi.setUserId(userId)
        val title = "Test Title"
        val content = "Test Content"
        val destination = "/test"
        val threadId = "threadId"

        val category = Category(
            id = UUID.randomUUID(),
            title = title,
            destination = destination,
            defaultActivated = true,
            topic = "ALL"
        )

        categorySpi.saveCategory(category)

        settingSpi.saveAllSetting(category, userId, false)

        detailSpi.save(category)

        detailApi.postGroupNotification(category.topic, content, threadId)

        detailSpi.findAllByUserIdOrderBySentAtDesc(userId)
            .forEach {
                assertThat(it.title).isEqualTo(title)
                assertThat(it.content).isEqualTo(content)
                assertThat(it.userId).isEqualTo(userId)
            }
    }

    @Test
    fun groupCategoryNotFound() {
        val content = "Test Content"
        val topic = "ALL"
        val threadId = "threadId"

        assertThrows<CategoryNotFoundException> { detailApi.postGroupNotification(topic, content, threadId) }
    }

    @Test
    fun postNotification() {
        val userId = UUID.randomUUID()
        val title = "Test Title"
        val content = "Test Content"
        val destination = "/test"
        val threadId = "threadId"

        val category = Category(
            id = UUID.randomUUID(),
            title = title,
            destination = destination,
            defaultActivated = true,
            topic = "ALL"
        )

        categorySpi.saveCategory(category)
        detailSpi.save(category)

        detailApi.postNotification(userId, category.topic, content, threadId)

        detailSpi.findAllByUserIdOrderBySentAtDesc(userId)
            .forEach {
                assertThat(it.title).isEqualTo(title)
                assertThat(it.content).isEqualTo(content)
                assertThat(it.userId).isEqualTo(userId)
            }
    }

    @Test
    fun singleCategoryNotFound() {
        val userId = UUID.randomUUID()
        val topic = "ALL"
        val content = "Test Content"
        val threadId = "threadId"

        assertThrows<CategoryNotFoundException> { detailApi.postNotification(userId, topic, content, threadId) }
    }
}
