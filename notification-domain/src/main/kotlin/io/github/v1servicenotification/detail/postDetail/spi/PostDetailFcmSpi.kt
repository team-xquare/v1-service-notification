package io.github.v1servicenotification.detail.postDetail.spi

interface PostDetailFcmSpi {
    fun sendMessage(token: String, title: String, content: String)
    fun sendGroupMessage(tokenList: List<String>, title: String, content: String)
}