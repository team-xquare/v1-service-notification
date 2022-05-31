package io.github.v1servicenotification.global.fcm

import com.google.firebase.messaging.*
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailFcmSpi
import io.github.v1servicenotification.global.fcm.dto.NotificationRequest
import org.springframework.stereotype.Service

@Service
class FcmService: PostDetailFcmSpi {

    override fun sendGroupMessage(tokenList: List<String>, title: String, content: String) {
        val multicast = MulticastMessage.builder()
            .addAllTokens(tokenList)
            .setNotification(
                Notification.builder()
                    .setTitle(title)
                    .setBody(content)
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

    override fun sendMessage(token: String, title: String, content: String) {
        val message = Message.builder()
            .setToken(token)
            .setNotification(
                Notification.builder()
                    .setTitle(title)
                    .setBody(content)
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