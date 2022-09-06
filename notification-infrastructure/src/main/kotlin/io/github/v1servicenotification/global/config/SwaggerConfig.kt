package io.github.v1servicenotification.global.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun publicApi(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("v1-notification-service")
        .pathsToMatch("/notifications/*")
        .build()

    @Bean
    fun springShopOpenApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("XQUARE-Notification API")
                    .description("XQUARE Notification의 Api 명세서입니다.")
                    .version("v0.0.0")
            )
    }

}