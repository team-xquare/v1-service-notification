package io.github.v1servicenotification.detail.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.exception.CategoryNotFoundException
import io.github.v1servicenotification.category.spi.QueryCategoryRepositorySpi
import io.github.v1servicenotification.detail.Detail
import io.github.v1servicenotification.detail.api.DetailApi
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
class DetailApiImpl(
    private val postDetailSettingRepositorySpi: PostDetailSettingRepositorySpi,
    private val postDetailRepositorySpi: PostDetailRepositorySpi,
    private val queryCategoryRepositorySpi: QueryCategoryRepositorySpi,
    private val postDetailFcmSpi: PostDetailFcmSpi,
    private val postDetailUserSpi: PostDetailUserSpi,
    private val queryDetailRepositorySpi: QueryDetailRepositorySpi
): DetailApi {

    override fun postGroupNotification(categoryId: UUID, title: String, content: String) {
        if (!queryCategoryRepositorySpi.exist(categoryId)) {
            throw CategoryNotFoundException.EXCEPTION
        }

        val userIdList = if (queryCategoryRepositorySpi.findById(categoryId).defaultActivated) {
            // 기본값이 true면 Setting에서 false로 설정한 사람을 제외하고 발송한다.
            postDetailUserSpi.getExcludeUserIdList(
                postDetailSettingRepositorySpi.findAllUserIdByCategoryIdAndIsActivated(categoryId, false)
            )
        } else {
            postDetailSettingRepositorySpi.findAllUserIdByCategoryIdAndIsActivated(categoryId, true)
        }


        val detailList = userIdList
            .stream()
            .map {
                Detail(
                    title = title,
                    content = content,
                    sentAt = LocalDateTime.now(),
                    isRead = false,
                    userId = it,
                    categoryId = categoryId
                )
            }.toList()

        postDetailRepositorySpi.saveAllDetail(detailList)

        postDetailFcmSpi.sendGroupMessage(postDetailUserSpi.getDeviceTokenList(userIdList), title, content)

    }

    override fun postNotification(userId: UUID, categoryId: UUID, title: String, content: String) {
        if(!queryCategoryRepositorySpi.exist(categoryId)) {
            throw CategoryNotFoundException.EXCEPTION
        }

        postDetailRepositorySpi.save(
            Detail(
                title = title,
                content = content,
                sentAt = LocalDateTime.now(),
                isRead = false,
                userId = userId,
                categoryId = categoryId,
            )
        )

        postDetailFcmSpi.sendMessage(postDetailUserSpi.getDeviceToken(userId), title, content)
    }

    override fun queryNotificationDetail(userId: UUID): DetailResponse {
        return DetailResponse(
            queryDetailRepositorySpi.findAllByUserId(userId)
                .map {
                    DetailElement(
                        id = it.id,
                        title = it.title,
                        content = it.content,
                        sentAt = it.sentAt,
                        isRead = it.isRead,
                        userId = it.userId,
                        categoryName = it.name,
                        destination = it.destination,
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
