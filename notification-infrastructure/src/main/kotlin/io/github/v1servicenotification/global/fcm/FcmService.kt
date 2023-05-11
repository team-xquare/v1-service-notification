package io.github.v1servicenotification.global.fcm

import com.google.firebase.messaging.Aps
import com.google.firebase.messaging.ApnsConfig
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification
import io.github.v1servicenotification.detail.spi.PostDetailFcmSpi
import io.github.v1servicenotification.error.NotificationDeviceTokenLengthException
import org.springframework.stereotype.Service

@Service
class FcmService: PostDetailFcmSpi {

    companion object {
        const val MAX_TOKEN_LENGTH = 163
    }

    override fun sendGroupMessage(tokenList: List<String>, title: String, content: String, threadId: String) {
        val validTokens = tokenList.filter { it.length == MAX_TOKEN_LENGTH }
        if (validTokens.isNotEmpty()) {
            val multicast = MulticastMessage.builder()
                .addAllTokens(validTokens)
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
                                .setThreadId(threadId)
                                .build()
                        ).build()
                )
                .build()

            FirebaseMessaging.getInstance().sendMulticastAsync(multicast)
        }
    }

    override fun sendMessage(token: String, title: String, content: String, threadId: String) {
        if (token.length >= MAX_TOKEN_LENGTH) {
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
                                .setThreadId(threadId)
                                .build()
                        ).build()
                )
                .build()
            FirebaseMessaging.getInstance().sendAsync(message)
        } else {
            throw NotificationDeviceTokenLengthException.EXCEPTION
        }
    }

}
