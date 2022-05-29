package io.github.v1servicenotification.stubs

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.detail.Detail
import io.github.v1servicenotification.detail.queryDetail.spi.QueryDetailRepositorySpi
import io.github.v1servicenotification.detail.queryDetail.spi.dto.DetailModel
import java.util.*

class InMemoryQueryDetailRepository(
    private val categoryMap: HashMap<UUID, Category> = hashMapOf(),
    private val detailMap: HashMap<UUID, Detail> = hashMapOf()
) : QueryDetailRepositorySpi {

    fun saveCategory(category: Category) {
        categoryMap[category.id] = category
    }

    fun saveDetail(detail: Detail) {
        detailMap[detail.id] = detail
    }

    override fun findAllByUserId(userId: UUID): List<DetailModel> {
        return detailMap.filter { it.value.userId == userId }
            .map {
                val category = categoryMap[it.value.categoryId]
                    ?: throw RuntimeException()
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

}