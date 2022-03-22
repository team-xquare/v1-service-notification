package io.github.v1servicenotification.domain.setting.service

import io.github.v1servicenotification.domain.category.domain.repository.NotificationCategoryRepository
import io.github.v1servicenotification.domain.setting.domain.NotificationSetting
import io.github.v1servicenotification.domain.setting.domain.SettingId
import io.github.v1servicenotification.domain.setting.domain.repository.NotificationSettingRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ActivateNotificationCategoryService(
        private val notificationCategoryRepository: NotificationCategoryRepository,
        private val notificationSettingRepository: NotificationSettingRepository
) {

    fun execute(categoryUUID: UUID) {
        val notificationCategory = notificationCategoryRepository.findById(categoryUUID)
                .orElseThrow {
                    RuntimeException() //TODO Error handling 구현 후 예외 처리하기
                }
        notificationSettingRepository.save(
                NotificationSetting(
                        settingId = SettingId(
                                userId = UUID.randomUUID(),//TODO UserId 받아서 넣기
                                notificationCategory = notificationCategory
                        ),
                    isActivated = true
                )
        )
    }

}