package io.github.v1servicenotification.setting

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.setting.activeSetting.service.ActivateCategoryImpl
import io.github.v1servicenotification.setting.facade.SettingFacade
import io.github.v1servicenotification.stubs.InMemoryQueryCategoryRepository
import io.github.v1servicenotification.stubs.InMemorySettingRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class ActivateCategoryImplTest {

    private val categorySpi = InMemoryQueryCategoryRepository()

    private val settingSpi = InMemorySettingRepository()

    private val settingFacade = SettingFacade(categorySpi, settingSpi)

    private val activateCategory = ActivateCategoryImpl(settingFacade)

    @Test
    fun activateCategory() {
        val userId = UUID.randomUUID()
        val categoryId = UUID.randomUUID()
        categorySpi.saveCategory(
            Category(categoryId, "Test name", "Test destination", false)
        )
        assertThat(activateCategory.activateCategory(categoryId, userId))
            .isEqualTo(201)
        assertThat(activateCategory.activateCategory(categoryId, userId))
            .isEqualTo(204)
    }

}