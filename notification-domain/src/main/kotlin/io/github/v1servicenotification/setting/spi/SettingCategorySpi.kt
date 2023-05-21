package io.github.v1servicenotification.setting.spi

import io.github.v1servicenotification.category.Category

interface SettingCategorySpi {
    fun findByStartingWithTopic(topic: String): List<Category>
}
