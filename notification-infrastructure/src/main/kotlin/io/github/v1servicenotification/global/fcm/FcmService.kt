package io.github.v1servicenotification.global.fcm

import com.google.firebase.messaging.Aps
import com.google.firebase.messaging.ApnsConfig
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification
import io.github.v1servicenotification.detail.spi.PostDetailFcmSpi
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
