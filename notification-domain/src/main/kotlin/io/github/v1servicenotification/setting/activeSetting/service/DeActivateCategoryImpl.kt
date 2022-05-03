package io.github.v1servicenotification.setting.activeSetting.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.setting.activeSetting.api.DeActivateCategory
import io.github.v1servicenotification.setting.facade.SettingFacade
import java.util.*

@DomainService
class DeActivateCategoryImpl(
    private val settingFacade: SettingFacade
) : DeActivateCategory {
    override fun deActivateCategory(categoryId: UUID, userId: UUID): Int {
        return settingFacade.saveOrUpdateSetting(
            categoryId = categoryId,
            userId = userId,
            isActivate = true
        )
    }
}