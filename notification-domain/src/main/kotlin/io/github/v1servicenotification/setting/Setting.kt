package io.github.v1servicenotification.setting

import io.github.v1servicenotification.annotation.Aggregate
import java.util.UUID

@Aggregate
class Setting(
    val userId: UUID,

    val notificationCategoryId: UUID,

    isActivated: Boolean
) {

    var isActivated = isActivated
        private set

    fun changeIsActivate(isActivated: Boolean) {
        this.isActivated = isActivated;
    }

}
