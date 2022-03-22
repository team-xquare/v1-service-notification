package io.github.v1servicenotification.domain.setting.service

import io.github.v1servicenotification.domain.setting.facade.NotificationSettingFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class DeActivateNotificationCategoryService(
        private val notificationSettingFacade: NotificationSettingFacade
) {

    @Transactional
    fun execute(categoryUUID: UUID): Int {
        return notificationSettingFacade.saveNotificationSetting(categoryUUID, false)
    }

}