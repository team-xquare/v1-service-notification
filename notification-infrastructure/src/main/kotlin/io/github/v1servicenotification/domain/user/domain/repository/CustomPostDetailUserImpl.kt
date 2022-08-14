package io.github.v1servicenotification.domain.user.domain.repository

import io.github.v1servicenotification.detail.postDetail.spi.PostDetailUserSpi
import io.github.v1servicenotification.infrastructure.feign.client.UserClient
import io.github.v1servicenotification.infrastructure.feign.error.FeignBadRequestException
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomPostDetailUserImpl(
    private val userClient: UserClient
): PostDetailUserSpi {
    override fun getExcludeUserIdList(userIdList: List<UUID>): List<UUID> {
        return userClient.getExcludeUserIdList(userIdList).userIdList
    }

    override fun getDeviceToken(userId: UUID): String {
        val result = userClient.token(listOf(userId)).tokens
        if(result.isEmpty()) {
            throw FeignBadRequestException.EXCEPTION
        }
        return result[0]
    }

    override fun getDeviceTokenList(userIdList: List<UUID>): List<String> {
        return userClient.token(userIdList).tokens
    }

}