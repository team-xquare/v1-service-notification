package io.github.v1servicenotification.setting.spi

import io.github.v1servicenotification.category.Category
import java.util.*

interface SettingCategorySpi {
    fun findByStartingWithTopic(topic: String): List<UUID>
}
