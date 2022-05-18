package io.github.v1servicenotification.setting

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.setting.activeSetting.service.DeActivateCategoryImpl
import io.github.v1servicenotification.setting.facade.SettingFacade
import io.github.v1servicenotification.stubs.InMemoryCategoryRepository
import io.github.v1servicenotification.stubs.InMemorySettingRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class DeActivateCategoryImplTest {

    private val categorySpi = InMemoryCategoryRepository()

    private val settingSpi = InMemorySettingRepository()

    private val settingFacade = SettingFacade(categorySpi, settingSpi)

    private val deActivateCategory = DeActivateCategoryImpl(settingFacade)

    @Test
    fun deActivatedCategory() {
        val userId = UUID.randomUUID()
        val categoryId = UUID.randomUUID()
        categorySpi.saveCategory(
            Category(categoryId, "Test name", "Test destination", false)
        )
        Assertions.assertThat(deActivateCategory.deActivateCategory(categoryId, userId))
            .isEqualTo(201)
        Assertions.assertThat(deActivateCategory.deActivateCategory(categoryId, userId))
            .isEqualTo(204)
    }

}