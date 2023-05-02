package io.github.v1servicenotification.detail.spi

import io.github.v1servicenotification.detail.Detail
import java.util.UUID

interface PostDetailRepositorySpi {
    fun save(detail: Detail)
    fun saveAllDetail(detailList: List<Detail>)
    fun updateAllDetailByUserIdAndIsReadFalse(userId: UUID)
}
