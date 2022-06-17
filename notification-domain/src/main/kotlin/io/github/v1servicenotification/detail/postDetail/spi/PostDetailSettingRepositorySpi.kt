package io.github.v1servicenotification.detail.postDetail.spi

import java.util.*

interface PostDetailSettingRepositorySpi {
    fun findAllUserIdByCategoryId(categoryId: UUID): List<UUID>
}