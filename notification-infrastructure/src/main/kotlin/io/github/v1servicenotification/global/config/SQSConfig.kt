package io.github.v1servicenotification.global.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.validation.Validator


@Configuration
class SQSConfig(
    private val validator: Validator
) {

    @Bean
    fun queueMessageHandlerFactory(): QueueMessageHandlerFactory {
        val objectMapper = ObjectMapper()
            .apply {
                registerModule(KotlinModule.Builder().build())
            }

        val messageConverter = MappingJackson2MessageConverter()
            .apply {
                this.objectMapper = objectMapper
                this.isStrictContentTypeMatch = false
            }

        val factory = QueueMessageHandlerFactory()
            .apply {
                setArgumentResolvers(
                    listOf(CustomPayloadMethodArgumentResolver(messageConverter, validator))
                )
            }

        return factory
    }
}
