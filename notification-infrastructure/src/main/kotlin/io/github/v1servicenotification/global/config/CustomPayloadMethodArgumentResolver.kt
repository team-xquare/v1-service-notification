package io.github.v1servicenotification.global.config

import org.springframework.core.MethodParameter
import org.springframework.messaging.Message
import org.springframework.messaging.converter.MessageConverter
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver
import org.springframework.validation.Validator
import org.springframework.web.bind.MethodArgumentNotValidException

class CustomPayloadMethodArgumentResolver(
    converter: MessageConverter,
    validator: Validator
) : HandlerMethodArgumentResolver {
    private val payloadMethodArgumentResolver =
        PayloadMethodArgumentResolver(converter, validator)

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return payloadMethodArgumentResolver.supportsParameter(parameter)
    }

    override fun resolveArgument(parameter: MethodParameter, message: Message<*>): Any? {
        return try {
             payloadMethodArgumentResolver.resolveArgument(parameter, message)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}