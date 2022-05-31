package io.github.v1servicenotification.global.config

import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver

@Configuration
class SQSConfig {

    @Bean
    fun queueMessageHandlerFactory(): QueueMessageHandlerFactory? {
        val factory = QueueMessageHandlerFactory()
        val messageConverter = MappingJackson2MessageConverter()

        messageConverter.isStrictContentTypeMatch = false

        factory.setArgumentResolvers(
            listOf(PayloadMethodArgumentResolver(messageConverter))
        )

        return factory
    }

}