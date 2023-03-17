package io.github.v1servicenotification.detail.spi

import io.github.v1servicenotification.detail.Detail

interface PostDetailRepositorySpi {
    fun save(detail: Detail)
    fun saveAllDetail(detailList: List<Detail>)
}
