package io.github.v1servicenotification.detail.spi

import io.github.v1servicenotification.annotation.Spi
import io.github.v1servicenotification.detail.spi.dto.DetailModel
import java.util.*

@Spi
interface QueryDetailRepositorySpi {
    fun findAllByUserId(userId: UUID): List<DetailModel>
    fun findAllByUseridAndIsReadFalse(userId: UUID): Int
}
