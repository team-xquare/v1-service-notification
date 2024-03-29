package io.github.v1servicenotification.detail.spi

import io.github.v1servicenotification.annotation.Spi
import io.github.v1servicenotification.detail.spi.dto.TopicDetailModel
import java.util.UUID

@Spi
interface QueryDetailRepositorySpi {
    fun findAllByUserIdOrderBySentAtDesc(userId: UUID): List<TopicDetailModel>
    fun findAllByUseridAndIsReadFalse(userId: UUID): Int
}
