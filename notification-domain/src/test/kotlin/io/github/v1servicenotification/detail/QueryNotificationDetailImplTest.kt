package io.github.v1servicenotification.detail

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.detail.queryDetail.service.QueryNotificationDetailImpl
import io.github.v1servicenotification.stubs.InMemoryDetailRepository
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*
import org.assertj.core.api.Assertions.*

class QueryNotificationDetailImplTest {

    private val detailSpi = InMemoryDetailRepository()
    private val queryNotificationDetail = QueryNotificationDetailImpl(detailSpi)

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

        assertThat(queryNotificationDetail.queryNotificationDetail(userId).notifications[0].userId)
            .isEqualTo(userId)
    }

}