package io.github.v1servicenotification.setting.activeSetting.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.setting.activeSetting.api.ActivateCategory
import io.github.v1servicenotification.setting.facade.SettingFacade
import java.util.*

@DomainService
class ActivateCategoryImpl(
    private val settingFacade: SettingFacade
) : ActivateCategory {
    override fun activateCategory(categoryId: UUID, userId: UUID): Int {
        return settingFacade.saveOrUpdateSetting(
            categoryId = categoryId,
            userId = userId,
            isActivate = true
        )
    }
}