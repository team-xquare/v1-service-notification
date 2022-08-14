package io.github.v1servicenotification.detail.postDetail.spi

import java.util.UUID

interface PostDetailUserSpi {
    fun getExcludeUserIdList(userIdList: List<UUID>): List<UUID>
    fun getDeviceToken(userId: UUID): String
    fun getDeviceTokenList(userIdList: List<UUID>): List<String>
}