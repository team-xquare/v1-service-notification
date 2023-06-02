package io.github.v1servicenotification.infrastructure.feign.client.dto.request

import java.util.UUID
import javax.annotation.Nullable

data class ExcludeUserIdsRequest(
    val userIdList: List<UUID>,
)
