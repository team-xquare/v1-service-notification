package io.github.v1servicenotification.detail.postDetail.spi

import java.util.*

interface PostDetailSettingRepositorySpi {
    fun findAllUserIdByCategoryIdAndIsActivated(categoryId: UUID, isActivated: Boolean): List<UUID>
}