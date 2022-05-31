package io.github.v1servicenotification.detail.postDetail.spi

import io.github.v1servicenotification.detail.Detail

interface PostDetailRepositorySpi {
    fun saveAllDetail(detailList: List<Detail>)
}