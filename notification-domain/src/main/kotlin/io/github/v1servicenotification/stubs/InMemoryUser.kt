package io.github.v1servicenotification.stubs

import io.github.v1servicenotification.detail.spi.PostDetailUserSpi
import java.util.*

class InMemoryUser: PostDetailUserSpi {

    private var userId: UUID = UUID.randomUUID()

    fun setUserId(userId: UUID) {
        this.userId = userId
    }

    override fun getExcludeUserIdList(userIdList: List<UUID>): List<UUID> {
        return listOf(userId)
    }

    override fun getDeviceToken(userId: UUID): String {
        return "Token"
    }

    override fun getDeviceTokenList(userIdList: List<UUID>): List<String> {
        return listOf("Test Token")
    }

}