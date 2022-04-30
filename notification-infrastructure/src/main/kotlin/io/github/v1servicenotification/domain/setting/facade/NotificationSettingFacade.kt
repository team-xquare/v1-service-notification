package io.github.v1servicenotification.domain.setting.facade

import io.github.v1servicenotification.domain.category.facade.CategoryFacade
import io.github.v1servicenotification.domain.setting.domain.SettingEntity
import io.github.v1servicenotification.domain.setting.domain.SettingId
import io.github.v1servicenotification.domain.setting.domain.repository.NotificationSettingRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.util.*

@Component
class NotificationSettingFacade(
        private val categoryFacade: CategoryFacade,
        private val notificationSettingRepository: NotificationSettingRepository
) {

    fun saveOrUpdateNotificationSetting(categoryUUID: UUID, isActivate: Boolean): Int {
        val notificationCategory = categoryFacade.getCategoryById(categoryUUID)

        val settingId = SettingId(
                userId = UUID.randomUUID(),//TODO UserId 받아서 넣기
                categoryEntity = notificationCategory
        )

        return if (notificationSettingRepository.findBySettingId(settingId) != null) {
            notificationSettingRepository.findBySettingId(settingId)!!
                    .changeIsActivate(isActivate)
            HttpStatus.NO_CONTENT.value()
        } else {
            notificationSettingRepository.save(
                    SettingEntity(
                            settingId = settingId,
                            isActivated = isActivate
                    )
            )
            HttpStatus.CREATED.value()
        }
    }

}