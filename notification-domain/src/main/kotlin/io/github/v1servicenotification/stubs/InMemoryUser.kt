package io.github.v1servicenotification.stubs

import io.github.v1servicenotification.detail.postDetail.spi.PostDetailUserSpi
import java.util.*

class InMemoryUser: PostDetailUserSpi {
    override fun getDeviceToken(userId: UUID): String {
        return "Token"
    }

    override fun getDeviceTokenList(userIdList: List<UUID>): List<String> {
        return listOf("Test Token")
    }

}