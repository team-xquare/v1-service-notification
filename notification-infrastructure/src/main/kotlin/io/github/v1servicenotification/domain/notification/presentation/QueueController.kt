package io.github.v1servicenotification.domain.notification.presentation

import io.github.v1servicenotification.detail.api.NotificationDetailApi
import io.github.v1servicenotification.domain.notification.presentation.dto.Group
import io.github.v1servicenotification.domain.notification.presentation.dto.Personal
import io.github.v1servicenotification.domain.notification.presentation.dto.SpecificGroup
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy.ALWAYS
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import javax.validation.Valid

@Component
class QueueController(
    private val notificationDetailApi: NotificationDetailApi
) {

    @SqsListener(value = ["group-notification.fifo"], deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    fun groupNotification(@Payload @Valid group: Group) {
        notificationDetailApi.postGroupNotification(group.topic, group.content, group.threadId)
    }

    @SqsListener(value = ["specific-group-notification.fifo"], deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    fun specificGroupNotification(@Payload @Valid specificGroup: SpecificGroup) {
        println(specificGroup.userIdList)
        notificationDetailApi.postSpecificGroupNotification(specificGroup.userIdList, specificGroup.topic, specificGroup.content, specificGroup.threadId)
    }

    @SqsListener(value = ["notification.fifo"], deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    fun notification(@Payload @Valid personal: Personal) {
        notificationDetailApi.postNotification(personal.userId, personal.topic, personal.content, personal.threadId)
    }
}
