package io.github.v1servicenotification.setting

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.setting.activeSetting.service.QueryActivatedCategoryImpl
import io.github.v1servicenotification.stubs.InMemorySettingRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class QueryActivatedCategoryImplTest {

    private val settingSpi = InMemorySettingRepository()

    private val queryActivatedCategory = QueryActivatedCategoryImpl(settingSpi)

    @Test
    fun queryActivatedCategory() {
        val userId = UUID.randomUUID()
        val categoryId = UUID.randomUUID()

        val category = Category(categoryId, "Test name", "Test destination", false)

        settingSpi.saveCategory(category)

        settingSpi.saveSetting(
            category,
            userId,
            true
        )

        val result = queryActivatedCategory.queryActivatedCategory(userId).categories[0]

        assertThat(result.id).isEqualTo(categoryId)
        assertThat(result.name).isEqualTo(category.name)
        assertThat(result.destination).isEqualTo(category.destination)

    }

}