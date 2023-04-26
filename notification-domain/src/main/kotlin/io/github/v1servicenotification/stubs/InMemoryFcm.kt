package io.github.v1servicenotification.stubs

import io.github.v1servicenotification.detail.spi.PostDetailFcmSpi

class InMemoryFcm: PostDetailFcmSpi {
    override fun sendMessage(token: String, title: String, content: String, threadId: String) {
        // TODO("Not yet implemented")
    }

    override fun sendGroupMessage(tokenList: List<String>, title: String, content: String, threadId: String) {
        // TODO("Not yet implemented")
    }
}
