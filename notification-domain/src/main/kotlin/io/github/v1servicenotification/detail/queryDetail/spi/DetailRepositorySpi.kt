package io.github.v1servicenotification.detail.queryDetail.spi

import io.github.v1servicenotification.annotation.Spi
import io.github.v1servicenotification.detail.Detail
import java.util.*

@Spi
interface DetailRepositorySpi {
    fun findAllByUserId(userId: UUID): List<Detail>
}