package io.github.v1servicenotification.detail.queryDetail.spi

import io.github.v1servicenotification.annotation.Spi
import io.github.v1servicenotification.detail.queryDetail.spi.dto.DetailModel
import java.util.*

@Spi
interface QueryDetailRepositorySpi {
    fun findAllByUserId(userId: UUID): List<DetailModel>
}