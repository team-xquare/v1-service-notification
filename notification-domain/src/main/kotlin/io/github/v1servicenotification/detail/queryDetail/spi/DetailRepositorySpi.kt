package io.github.v1servicenotification.detail.queryDetail.spi

import io.github.v1servicenotification.annotation.Spi
import io.github.v1servicenotification.detail.NotificationDetail
import java.util.*

@Spi
interface DetailRepositorySpi {
    suspend fun findAllByUserId(userId: UUID): List<NotificationDetail>
}