package io.github.v1servicenotification.setting.activeSetting.api

import java.util.*

interface ActivateCategory {
    fun activateCategory(categoryId: UUID, userId: UUID): Int
}