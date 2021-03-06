package io.github.v1servicenotification.stubs

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.updateCategory.exception.CategoryNotFoundException
import io.github.v1servicenotification.detail.Detail
import io.github.v1servicenotification.detail.exception.NotificationDetailNotFoundException
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailRepositorySpi
import io.github.v1servicenotification.detail.queryDetail.spi.QueryDetailRepositorySpi
import io.github.v1servicenotification.detail.queryDetail.spi.dto.DetailModel
import java.util.*

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

    override fun findAllByUserId(userId: UUID): List<DetailModel> {
        return detailMap.filter { it.value.userId == userId }
            .map {
                val category = categoryMap[it.value.categoryId]
                    ?: throw CategoryNotFoundException.EXCEPTION
                DetailModel(
                    it.value.id,
                    it.value.title,
                    it.value.content,
                    it.value.sentAt,
                    it.value.isRead,
                    it.value.userId,
                    category.name,
                    category.destination
                )
            }
    }

    override fun saveAllDetail(detailList: List<Detail>) {
        detailList.forEach {
            save(it)
        }
    }

    override fun checkDetailByUserIdAndDetailId(userId: UUID, detailId: UUID) {
        val detail = detailMap[detailId]
            ?: throw NotificationDetailNotFoundException.EXCEPTION

        if(detail.userId != userId) {
            throw NotificationDetailNotFoundException.EXCEPTION
        }

        detail.checkRead()
    }

}