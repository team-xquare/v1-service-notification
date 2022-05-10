package io.github.v1servicenotification.stubs

import io.github.v1servicenotification.category.queryCategory.spi.CategoryRepositorySpi
import io.github.v1servicenotification.detail.Detail
import io.github.v1servicenotification.detail.queryDetail.spi.DetailRepositorySpi
import io.github.v1servicenotification.detail.queryDetail.spi.dto.DetailModel
import java.util.*

class InMemoryDetailRepository(
    private val categoryRepositorySpi: CategoryRepositorySpi,
    private val detailMap: HashMap<UUID, Detail> = hashMapOf()
) : DetailRepositorySpi {

    fun saveDetail(detail: Detail) {
        detailMap[detail.id] = detail
    }

    override fun findAllByUserId(userId: UUID): List<DetailModel> {
        return detailMap.filter { it.value.userId == userId }
            .map {
                val category = categoryRepositorySpi.findById(it.value.notificationCategoryId)
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