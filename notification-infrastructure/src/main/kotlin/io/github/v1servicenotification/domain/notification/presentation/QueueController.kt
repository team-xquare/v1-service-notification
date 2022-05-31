package io.github.v1servicenotification.domain.notification.presentation

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.v1servicenotification.detail.postDetail.api.PostGroupNotification
import io.github.v1servicenotification.detail.postDetail.api.PostNotification
import io.github.v1servicenotification.domain.notification.exception.EmptyArgumentException
import io.github.v1servicenotification.domain.notification.presentation.dto.Group
import io.github.v1servicenotification.domain.notification.presentation.dto.Personal
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import java.util.*

@Component
class QueueController(
    private val postGroupNotification: PostGroupNotification,
    private val postNotification: PostNotification
) {

    @SqsListener(value = ["group-notification.fifo"], deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    fun groupNotification(@Payload group: Group) {
        if(group.categoryId == null || group.title == null || group.content == null) {
            throw EmptyArgumentException.EXCEPTION
        }

        postGroupNotification.postGroupNotification(group.categoryId, group.title, group.content)
    }

    @SqsListener(value = ["notification.fifo"], deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    fun notification(@Payload personal: Personal) {
        if(personal.userId == null || personal.categoryId == null || personal.title == null || personal.content == null) {
            throw EmptyArgumentException.EXCEPTION
        }

        postNotification.postNotification(personal.userId, personal.categoryId, personal.title, personal.content)
    }

}