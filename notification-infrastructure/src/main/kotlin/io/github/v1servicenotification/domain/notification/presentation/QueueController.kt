package io.github.v1servicenotification.domain.notification.presentation

import io.github.v1servicenotification.detail.api.DetailApi
import io.github.v1servicenotification.domain.notification.presentation.dto.Group
import io.github.v1servicenotification.domain.notification.presentation.dto.Personal
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import javax.validation.Valid

@Component
class QueueController(
    private val detailApi: DetailApi
) {

    @SqsListener(value = ["group-notification.fifo"], deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    fun groupNotification(@Payload @Valid group: Group) {
        detailApi.postGroupNotification(group.categoryId, group.title, group.content)
    }

    @SqsListener(value = ["notification.fifo"], deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    fun notification(@Payload @Valid personal: Personal) {
        detailApi.postNotification(personal.userId, personal.categoryId, personal.title, personal.content)
    }

}
