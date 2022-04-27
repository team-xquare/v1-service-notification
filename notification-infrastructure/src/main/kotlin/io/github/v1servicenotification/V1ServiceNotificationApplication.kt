package io.github.v1servicenotification

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class V1ServiceNotificationApplication

fun main(args: Array<String>) {
    runApplication<V1ServiceNotificationApplication>(*args)
}
