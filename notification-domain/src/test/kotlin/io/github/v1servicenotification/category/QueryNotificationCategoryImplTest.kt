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
    fun 기본_카테고리_존재() {
        queryCategorySpi.saveCategory(
            Category(
                UUID.randomUUID(),
                "Test category",
                "Test destination",
                true
            )
        )
        assertThat(queryNotificationCategory.queryNotificationCategory(true).categories.size)
            .isEqualTo(1)
    }

    @Test
    fun 기본_카테고리_미존재() {
        queryCategorySpi.saveCategory(
            Category(
                UUID.randomUUID(),
                "Test category",
                "Test destination",
                false
            )
        )

        assertThat(queryNotificationCategory.queryNotificationCategory(true).categories.size)
            .isEqualTo(0)
    }

    @Test
    fun 비기본_카테고리_존재() {
        queryCategorySpi.saveCategory(
            Category(
                UUID.randomUUID(),
                "Test category",
                "Test destination",
                false
            )
        )
        assertThat(queryNotificationCategory.queryNotificationCategory(false).categories.size)
            .isEqualTo(1)
    }

    @Test
    fun 비기본_카테고리_미존재() {
        queryCategorySpi.saveCategory(
            Category(
                UUID.randomUUID(),
                "Test category",
                "Test destination",
                true
            )
        )

        assertThat(queryNotificationCategory.queryNotificationCategory(false).categories.size)
            .isEqualTo(0)
    }

}