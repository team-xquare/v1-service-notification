package io.github.v1servicenotification.detail.postDetail.spi

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.setting.Setting

interface PostDetailSettingRepositorySpi {
    fun findSettingByCategory(category: Category): List<Setting>
}