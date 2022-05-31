package io.github.v1servicenotification.domain.notification.presentation

import io.github.v1servicenotification.detail.postDetail.api.PostGroupNotification
import io.github.v1servicenotification.detail.postDetail.api.PostNotification
import io.github.v1servicenotification.domain.notification.presentation.dto.Group
import io.github.v1servicenotification.domain.notification.presentation.dto.Personal
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.messaging.converter.MessageConversionException
import org.springframework.messaging.handler.annotation.MessageExceptionHandler
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*
import javax.validation.Valid


@Component
class QueueController(
    private val postGroupNotification: PostGroupNotification,
    private val postNotification: PostNotification
) {

    @SqsListener(value = ["group-notification.fifo"], deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    fun groupNotification(@Payload @Valid group: Group) {
        postGroupNotification.postGroupNotification(group.categoryId!!, group.title!!, group.content!!)
    }

    @SqsListener(value = ["notification.fifo"], deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    fun notification(@Payload @Valid personal: Personal) {
        postNotification.postNotification(personal.userId!!, personal.categoryId!!, personal.title!!, personal.content!!)
    }

}