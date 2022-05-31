package io.github.v1servicenotification.global.fcm

import com.google.firebase.messaging.*
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailFcmSpi
import io.github.v1servicenotification.global.fcm.dto.NotificationRequest
import org.springframework.stereotype.Service

@Service
class FcmService: PostDetailFcmSpi {

    override fun sendGroupMessage(tokenList: List<String>, title: String, message: String) {
        val multicast = MulticastMessage.builder()
            .addAllTokens(tokenList)
            .setNotification(
                Notification.builder()
                    .setTitle(title)
                    .setBody(message)
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

        FirebaseMessaging.getInstance().sendMulticastAsync(multicast)
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