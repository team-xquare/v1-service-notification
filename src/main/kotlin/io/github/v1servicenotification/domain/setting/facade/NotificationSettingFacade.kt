package io.github.v1servicenotification.domain.setting.facade

import io.github.v1servicenotification.domain.category.domain.repository.NotificationCategoryRepository
import io.github.v1servicenotification.domain.setting.domain.NotificationSetting
import io.github.v1servicenotification.domain.setting.domain.SettingId
import io.github.v1servicenotification.domain.setting.domain.repository.NotificationSettingRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.util.*

@Component
class NotificationSettingFacade(
        private val notificationCategoryRepository: NotificationCategoryRepository,
        private val notificationSettingRepository: NotificationSettingRepository
) {

    fun saveOrUpdateNotificationSetting(categoryUUID: UUID, isActivate: Boolean): Int {
        val notificationCategory = notificationCategoryRepository.findById(categoryUUID)
                .orElseThrow {
                    RuntimeException() //TODO Error handling 구현 후 예외 처리하기
                }

        val settingId = SettingId(
                userId = UUID.randomUUID(),//TODO UserId 받아서 넣기
                notificationCategory = notificationCategory
        )

        return if (notificationSettingRepository.findBySettingId(settingId) != null) {
            notificationSettingRepository.findBySettingId(settingId)!!
                    .changeIsActivate(isActivate)
            HttpStatus.NO_CONTENT.value()
        } else {
            notificationSettingRepository.save(
                    NotificationSetting(
                            settingId = settingId,
                            isActivated = isActivate
                    )
            )
            HttpStatus.CREATED.value()
        }
    }

}