package io.github.v1servicenotification.global.fcm

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.io.IOException
import javax.annotation.PostConstruct

@Configuration
class FcmConfig {

    companion object {
        @Value("\${fcm.path}")
        lateinit var path: String
    }

    @PostConstruct
    fun initialize() {
        try {
            if(FirebaseApp.getApps().isEmpty()) {
                val options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(ClassPathResource(path).inputStream))
                    .build()
                FirebaseApp.initializeApp(options)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}