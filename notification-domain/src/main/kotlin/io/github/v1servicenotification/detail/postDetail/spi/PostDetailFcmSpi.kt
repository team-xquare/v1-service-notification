package io.github.v1servicenotification.detail.postDetail.spi

interface PostDetailFcmSpi {
    fun sendMessageByUserIdList(tokenList: List<String>, title: String, message: String)
}