package io.github.v1servicenotification.global.config

import com.amazonaws.services.sqs.AmazonSQSAsync
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.converter.MessageConverter
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver
import org.springframework.validation.Validator


@Configuration
class SQSConfig(
    private val validator: Validator
) {

    @Bean
    fun queueMessageHandlerFactory(): QueueMessageHandlerFactory {
        val factory = QueueMessageHandlerFactory()
        val messageConverter = MappingJackson2MessageConverter()
        val objectMapper = ObjectMapper()

        objectMapper.registerModule(KotlinModule.Builder().build())

        messageConverter.objectMapper = objectMapper

        messageConverter.isStrictContentTypeMatch = false

        factory.setArgumentResolvers(
            listOf(CustomPayloadMethodArgumentResolver(messageConverter, validator))
        )

        return factory
    }

}