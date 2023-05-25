package io.github.v1servicenotification.setting

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.setting.service.SettingApiImpl
import io.github.v1servicenotification.stubs.InMemoryCategoryRepository
import io.github.v1servicenotification.stubs.InMemorySettingRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.UUID

class SettingApiImplTest {

    private val categorySpi = InMemoryCategoryRepository()

    private val settingSpi = InMemorySettingRepository()

    private val settingApi = SettingApiImpl(settingSpi, categorySpi)

    @Test
    fun queryUserCategoryStatus() {
        val userId = UUID.randomUUID()
        val categoryId = UUID.randomUUID()

        val category = Category(categoryId, "Test name", "Test destination", false, "ALL")
        val setting = Setting(categoryId, userId, true)

        settingSpi.saveCategory(category)

        settingSpi.saveSetting(
            category,
            userId,
            true
        )

        val result = settingApi.queryUserCategoryStatus(userId).settings.first()

        assertThat(result.topic).isEqualTo(category.topic)
        assertThat(result.isActivate).isEqualTo(setting.isActivated)
    }
}
