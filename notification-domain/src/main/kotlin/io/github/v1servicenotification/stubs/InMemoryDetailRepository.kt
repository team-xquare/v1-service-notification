package io.github.v1servicenotification.stubs

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.exception.CategoryNotFoundException
import io.github.v1servicenotification.detail.Detail
import io.github.v1servicenotification.detail.spi.PostDetailRepositorySpi
import io.github.v1servicenotification.detail.spi.QueryDetailRepositorySpi
import io.github.v1servicenotification.detail.spi.dto.TopicDetailModel
import java.util.UUID

class InMemoryDetailRepository(
    private val categoryMap: HashMap<UUID, Category> = hashMapOf(),
    private val detailMap: HashMap<UUID, Detail> = hashMapOf()
) : QueryDetailRepositorySpi, PostDetailRepositorySpi {

    fun save(category: Category) {
        categoryMap[category.id] = category
    }

    override fun save(detail: Detail) {
        detailMap[detail.id] = detail
    }

    override fun findAllByUserIdOrderBySentAtDesc(userId: UUID): List<TopicDetailModel> {
        return detailMap.filter { it.value.userId == userId }
            .map {
                val category = categoryMap[it.value.categoryId]
                    ?: throw CategoryNotFoundException.EXCEPTION
                TopicDetailModel(
                    it.value.id,
                    it.value.title,
                    it.value.content,
                    it.value.sentAt,
                    it.value.isRead,
                    it.value.userId,
                    category.topic,
                )
            }.sortedByDescending { it.sentAt }
    }

    override fun findAllByUseridAndIsReadFalse(userId: UUID): Int {
        return detailMap.filter { it.value.userId == userId && !it.value.isRead }
            .size
    }

    override fun saveAllDetail(detailList: List<Detail>) {
        detailList.forEach {
            save(it)
        }
    }

    override fun updateAllDetailByUserIdAndIsReadFalse(userId: UUID) {
        detailMap.filter { it.value.userId == userId && !it.value.isRead }.map {
            it.value.checkRead()
        }
    }
}
