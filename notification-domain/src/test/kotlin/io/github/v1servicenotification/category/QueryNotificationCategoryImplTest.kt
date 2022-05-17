package io.github.v1servicenotification.category

import io.github.v1servicenotification.category.queryCategory.service.QueryNotificationCategoryImpl
import io.github.v1servicenotification.stubs.InMemoryQueryCategoryRepository
import org.junit.jupiter.api.Test
import java.util.*
import org.assertj.core.api.Assertions.*

class QueryNotificationCategoryImplTest {

    private val notificationSpi = InMemoryQueryCategoryRepository()
    private val queryNotificationCategory = QueryNotificationCategoryImpl(notificationSpi)

    @Test
    fun queryNotificationCategory() {
        notificationSpi.saveCategory(
            Category(
                UUID.randomUUID(),
                "Test category",
                "Test destination",
                true
            )
        )
        assertThat(queryNotificationCategory.queryNotificationCategory().categories.size)
            .isEqualTo(1)
    }

    @Test
    fun queryNotificationCategoryEmpty() {
        notificationSpi.saveCategory(
            Category(
                UUID.randomUUID(),
                "Test category",
                "Test destination",
                false
            )
        )

        assertThat(queryNotificationCategory.queryNotificationCategory().categories.size)
            .isEqualTo(0)
    }

}