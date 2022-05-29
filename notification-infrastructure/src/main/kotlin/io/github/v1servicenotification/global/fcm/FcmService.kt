package io.github.v1servicenotification.global.fcm

import com.google.firebase.messaging.*
import io.github.v1servicenotification.global.fcm.dto.NotificationRequest
import org.springframework.stereotype.Service

@Service
class FcmService {

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