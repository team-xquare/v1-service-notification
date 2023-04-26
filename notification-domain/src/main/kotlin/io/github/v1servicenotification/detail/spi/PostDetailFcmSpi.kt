package io.github.v1servicenotification.detail.spi

interface PostDetailFcmSpi {
    fun sendMessage(token: String, title: String, content: String, threadId: String)
    fun sendGroupMessage(tokenList: List<String>, title: String, content: String, threadId: String)
}
