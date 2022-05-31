package io.github.v1servicenotification.global.fcm

import com.google.firebase.messaging.*
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailFcmSpi
import io.github.v1servicenotification.global.fcm.dto.NotificationRequest
import org.springframework.stereotype.Service

@Service
class FcmService: PostDetailFcmSpi {

    override fun sendMessageByUserIdList(tokenList: List<String>, title: String, message: String) {
        tokenList
            .stream()
            .map {
                sendMessage(NotificationRequest(it, title, message))
            }
    }

    private fun sendMessage(request: NotificationRequest) {
        val message = Message.builder()
            .setToken(request.token)
            .setNotification(
                Notification.builder()
                    .setTitle(request.title)
                    .setBody(request.message)
                    .build()
            )
            .setApnsConfig(
                ApnsConfig.builder()
                    .setAps(
                        Aps.builder()
                            .setSound("default")
                            .build()
                    ).build()
            )
            .build()
        FirebaseMessaging.getInstance().sendAsync(message)
    }

}