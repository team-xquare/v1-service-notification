package io.github.v1servicenotification.domain.user.domain.repository

import io.github.v1servicenotification.detail.spi.PostDetailUserSpi
import io.github.v1servicenotification.infrastructure.feign.client.UserClient
import io.github.v1servicenotification.infrastructure.feign.client.dto.request.GetUserIdListRequest
import io.github.v1servicenotification.infrastructure.feign.error.FeignBadRequestException
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CustomPostDetailUserImpl(
    private val userClient: UserClient
) : PostDetailUserSpi {
    override fun getExcludeUserIdList(userIdList: List<UUID>): List<UUID> {
        val excludeUserIdList = GetUserIdListRequest(userIdList)
        return userClient.getExcludeUserIdList(excludeUserIdList).userIdList
    }

    override fun getDeviceToken(userId: UUID): String {
        val result = userClient.token(GetUserIdListRequest(listOf(userId))).tokens

        if (result.isEmpty()) {
            throw FeignBadRequestException.EXCEPTION
        }
        return result[0]
    }

    override fun getDeviceTokenList(userIdList: List<UUID>): List<String> {
        return userClient.token(GetUserIdListRequest(userIdList)).tokens
    }
}
