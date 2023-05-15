package io.github.v1servicenotification.infrastructure.feign.client.dto.request

import java.util.UUID
import javax.annotation.Nullable

data class ExcludeUserIdsRequest(
    @field:Nullable
    val userIds: List<UUID>?,
)
