package io.github.v1servicenotification.setting

import io.github.v1servicenotification.annotation.Aggregate
import io.github.v1servicenotification.category.NotificationCategory
import java.util.*

@Aggregate
class NotificationSetting(
    val userId: UUID,

    val notificationCategory: NotificationCategory,

    isActivated: Boolean
) {

    var isActivated = isActivated
        private set

    fun changeIsActivate(isActivated: Boolean) {
        this.isActivated = isActivated;
    }

}