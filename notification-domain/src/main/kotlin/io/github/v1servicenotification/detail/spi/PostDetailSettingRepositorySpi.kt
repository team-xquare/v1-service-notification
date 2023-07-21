package io.github.v1servicenotification.detail.spi

import java.util.UUID

interface PostDetailSettingRepositorySpi {
    fun findAllUserIdByTopicAndIsActivated(topic: String, isActivated: Boolean): List<UUID>
    fun findIsActivatedByUserIdAndTopic(userId: UUID, topic: String): Boolean
}
