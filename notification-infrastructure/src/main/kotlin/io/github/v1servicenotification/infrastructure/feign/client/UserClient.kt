package io.github.v1servicenotification.infrastructure.feign.client

import io.github.v1servicenotification.infrastructure.feign.client.dto.request.GetUserIdListRequest
import io.github.v1servicenotification.infrastructure.feign.client.dto.response.TokenResponse
import io.github.v1servicenotification.infrastructure.feign.client.dto.response.UserIdListResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "UserClient", url = "\${service.scheme}://\${service.user.host}")
interface UserClient {

    @PostMapping("/users/device-token")
    fun token(@RequestBody request: GetUserIdListRequest): TokenResponse

    @PostMapping("/users/exclude")
    fun getExcludeUserIdList(@RequestBody request: GetUserIdListRequest): UserIdListResponse
}
