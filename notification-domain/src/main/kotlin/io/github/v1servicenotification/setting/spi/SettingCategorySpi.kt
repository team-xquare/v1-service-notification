package io.github.v1servicenotification.setting.spi

import java.util.*

interface SettingCategorySpi {
    fun findByStartingWithTopic(topic: String): List<UUID>
}
