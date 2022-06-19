package io.github.v1servicenotification.infrastructure.feign.client

import io.github.v1servicenotification.infrastructure.feign.client.dto.response.TokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

@FeignClient(name = "UserClient", url = "\${user.url}")
interface UserClient {

    @GetMapping("/token/fcm")
    fun token(@RequestParam("users") userList: List<UUID>): TokenResponse

}