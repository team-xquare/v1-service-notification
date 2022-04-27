package io.github.v1servicenotification.category.queryCategory.spi

import io.github.v1servicenotification.annotation.Spi
import io.github.v1servicenotification.category.NotificationCategory

@Spi
interface CategoryRepositorySpi {
    suspend fun findAllByDefaultActivatedIsTrue(): List<NotificationCategory>
}