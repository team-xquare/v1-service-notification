package io.github.v1servicenotification.setting.activeSetting.api

import java.util.*

interface DeActivateCategory {
    fun deActivateCategory(categoryId: UUID, userId: UUID): Int
}