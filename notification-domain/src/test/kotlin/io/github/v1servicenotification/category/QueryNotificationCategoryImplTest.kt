package io.github.v1servicenotification.category

import io.github.v1servicenotification.category.queryCategory.service.QueryNotificationCategoryImpl
import io.github.v1servicenotification.stubs.InMemoryCategoryRepository
import org.junit.jupiter.api.Test
import java.util.*
import org.assertj.core.api.Assertions.*

class QueryNotificationCategoryImplTest {

    private val queryCategorySpi = InMemoryCategoryRepository()
    private val queryNotificationCategory = QueryNotificationCategoryImpl(queryCategorySpi)

    @Test
    fun queryNotificationCategory() {
        queryCategorySpi.saveCategory(
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
        queryCategorySpi.saveCategory(
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