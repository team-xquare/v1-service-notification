package io.github.v1servicenotification.detail.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.exception.CategoryNotFoundException
import io.github.v1servicenotification.category.spi.QueryCategoryRepositorySpi
import io.github.v1servicenotification.detail.Detail
import io.github.v1servicenotification.detail.api.NotificationDetailApi
import io.github.v1servicenotification.detail.api.dto.response.DetailElement
import io.github.v1servicenotification.detail.api.dto.response.DetailResponse
import io.github.v1servicenotification.detail.api.dto.response.NotificationCountResponse
import io.github.v1servicenotification.detail.spi.PostDetailFcmSpi
import io.github.v1servicenotification.detail.spi.PostDetailRepositorySpi
import io.github.v1servicenotification.detail.spi.PostDetailSettingRepositorySpi
import io.github.v1servicenotification.detail.spi.PostDetailUserSpi
import io.github.v1servicenotification.detail.spi.QueryDetailRepositorySpi
import java.time.LocalDateTime
import java.util.UUID

@DomainService
class NotificationDetailApiImpl(
    private val postDetailSettingRepositorySpi: PostDetailSettingRepositorySpi,
    private val postDetailRepositorySpi: PostDetailRepositorySpi,
    private val queryCategoryRepositorySpi: QueryCategoryRepositorySpi,
    private val postDetailFcmSpi: PostDetailFcmSpi,
    private val postDetailUserSpi: PostDetailUserSpi,
    private val queryDetailRepositorySpi: QueryDetailRepositorySpi,
) : NotificationDetailApi {

    override fun postGroupNotification(topic: String, content: String, threadId: String) {
        if (!queryCategoryRepositorySpi.existByTopic(topic)) {
            throw CategoryNotFoundException.EXCEPTION
        }

        val category = queryCategoryRepositorySpi.findByTopic(topic)

        val userIdList = if (category.defaultActivated) {
            // 기본값이 true면 Setting에서 false로 설정한 사람을 제외하고 발송한다.
            postDetailUserSpi.getExcludeUserIdList(
                postDetailSettingRepositorySpi.findAllUserIdByTopicAndIsActivated(topic, false)
            )
        } else {
            postDetailSettingRepositorySpi.findAllUserIdByTopicAndIsActivated(topic, true)
        }

        val detailList = userIdList
            .map {
                Detail(
                    title = category.title,
                    content = content,
                    sentAt = LocalDateTime.now(),
                    isRead = false,
                    userId = it,
                    categoryId = category.id,
                )
            }

        postDetailRepositorySpi.saveAllDetail(detailList)

        postDetailFcmSpi.sendGroupMessage(
            postDetailUserSpi.getDeviceTokenList(userIdList),
            category.title,
            content,
            threadId,
            category.destination
        )
    }

    override fun postSpecificGroupNotification(userIdList: List<UUID>, topic: String, content: String, threadId: String) {
        if (!queryCategoryRepositorySpi.existByTopic(topic)) {
            throw CategoryNotFoundException.EXCEPTION
        }

        val category = queryCategoryRepositorySpi.findByTopic(topic)

        val detailList = userIdList
            .map {
                Detail(
                    title = category.title,
                    content = content,
                    sentAt = LocalDateTime.now(),
                    isRead = false,
                    userId = it,
                    categoryId = category.id,
                )
            }

        postDetailRepositorySpi.saveAllDetail(detailList)

        postDetailFcmSpi.sendGroupMessage(
            postDetailUserSpi.getDeviceTokenList(userIdList),
            category.title,
            content,
            threadId,
            category.destination
        )
    }

    override fun postNotification(userId: UUID, topic: String, content: String, threadId: String) {
        if (!queryCategoryRepositorySpi.existByTopic(topic)) {
            throw CategoryNotFoundException.EXCEPTION
        }

        val category = queryCategoryRepositorySpi.findByTopic(topic)

        if (category.topic == "FEED_NOTICE_LIKE" || category.topic == "FEED_BAMBOO_LIKE") {
            sendMessage(category, userId, topic, content, threadId)
        } else {
            sendMessage(category, userId, topic, content, threadId)
            postDetailRepositorySpi.save(
                Detail(
                    title = category.title,
                    content = content,
                    sentAt = LocalDateTime.now(),
                    isRead = false,
                    userId = userId,
                    categoryId = category.id,
                )
            )
        }
    }

    private fun sendMessage(category: Category, userId: UUID, topic: String, content: String, threadId: String) {
        if (category.defaultActivated && postDetailSettingRepositorySpi.findIsActivatedByUserIdAndTopic(userId, topic)) {
            // 기본값이 true면 Setting에서 false로 설정한 사람을 제외하고 발송한다.
            postDetailFcmSpi.sendMessage(postDetailUserSpi.getDeviceToken(userId), category.title, content, threadId, category.destination)
        }
    }

    override fun queryNotificationDetail(userId: UUID): DetailResponse {
        postDetailRepositorySpi.updateAllDetailByUserIdAndIsReadFalse(userId)
        return DetailResponse(
            queryDetailRepositorySpi.findAllByUserIdOrderBySentAtDesc(userId)
                .map {
                    DetailElement(
                        id = it.id,
                        title = it.title,
                        content = it.content,
                        sentAt = it.sentAt,
                        isRead = it.isRead,
                        userId = it.userId,
                        topic = it.topic,
                    )
                }
                .toList()
        )
    }

    override fun queryUnreadNotificationCount(userId: UUID): NotificationCountResponse {
        return NotificationCountResponse(
            queryDetailRepositorySpi.findAllByUseridAndIsReadFalse(userId)
        )
    }
}
