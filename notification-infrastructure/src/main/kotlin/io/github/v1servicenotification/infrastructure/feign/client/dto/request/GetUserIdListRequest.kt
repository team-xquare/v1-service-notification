package io.github.v1servicenotification.infrastructure.feign.client.dto.request

import java.util.UUID

data class GetUserIdListRequest(
    val userIdList: List<UUID>
)