package io.github.v1servicenotification.setting

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.setting.service.SettingApiImpl
import io.github.v1servicenotification.stubs.InMemoryCategoryRepository
import io.github.v1servicenotification.stubs.InMemorySettingRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class SettingApiImplTest {

    private val categorySpi = InMemoryCategoryRepository()

    private val settingSpi = InMemorySettingRepository()

    private val settingApi = SettingApiImpl(settingSpi, categorySpi)

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

        val result = settingApi.queryActivatedCategory(userId).categories[0]

        assertThat(result.id).isEqualTo(categoryId)
        assertThat(result.name).isEqualTo(category.name)
        assertThat(result.destination).isEqualTo(category.destination)

    }

    @Test
    fun deActivatedCategory() {
        val userId = UUID.randomUUID()
        val categoryId = UUID.randomUUID()
        categorySpi.saveCategory(
            Category(categoryId, "Test name", "Test destination", false)
        )
        Assertions.assertThat(settingApi.deActivateCategory(categoryId, userId))
            .isEqualTo(201)
        Assertions.assertThat(settingApi.deActivateCategory(categoryId, userId))
            .isEqualTo(204)
    }

    @Test
    fun activateCategory() {
        val userId = UUID.randomUUID()
        val categoryId = UUID.randomUUID()
        categorySpi.saveCategory(
            Category(categoryId, "Test name", "Test destination", false)
        )
        assertThat(settingApi.activateCategory(categoryId, userId))
            .isEqualTo(201)
        assertThat(settingApi.activateCategory(categoryId, userId))
            .isEqualTo(204)
    }

}