package io.github.v1servicenotification.detail

import io.github.v1servicenotification.detail.exception.NotificationDetailNotFoundException
import io.github.v1servicenotification.detail.postDetail.service.CheckNotificationImpl
import io.github.v1servicenotification.stubs.InMemoryDetailRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.util.*
import org.assertj.core.api.AssertionsForClassTypes.assertThat


class CheckNotificationImplTest {

    private val postDetailRepositorySpi = InMemoryDetailRepository()
    private val checkNotification = CheckNotificationImpl(postDetailRepositorySpi)

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

        postDetailRepositorySpi.save(detail)

        checkNotification.checkNotification(userId, detailId)

        assertThat(detail.isRead).isEqualTo(true)
    }

    @Test
    fun detailNotFound() {
        val userId = UUID.randomUUID()
        val detailId = UUID.randomUUID()

        assertThrows<NotificationDetailNotFoundException> {
            checkNotification.checkNotification(userId, detailId)
        }
    }

    @Test
    fun detailNotFoundUser() {
        val userId = UUID.randomUUID()
        val detailId = UUID.randomUUID()

        postDetailRepositorySpi.save(
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
            checkNotification.checkNotification(userId, detailId)
        }
    }

}