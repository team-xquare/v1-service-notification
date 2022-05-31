package io.github.v1servicenotification.detail.postDetail.spi

interface PostDetailFcmSpi {
    fun sendGroupMessage(tokenList: List<String>, title: String, content: String)
}