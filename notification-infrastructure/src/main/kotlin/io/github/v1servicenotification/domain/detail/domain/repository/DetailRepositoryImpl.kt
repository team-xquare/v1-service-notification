package io.github.v1servicenotification.domain.detail.domain.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import io.github.v1servicenotification.detail.queryDetail.spi.DetailRepositorySpi
import io.github.v1servicenotification.detail.queryDetail.spi.dto.DetailModel
import io.github.v1servicenotification.domain.category.domain.QCategoryEntity.categoryEntity
import io.github.v1servicenotification.domain.detail.domain.QDetailEntity.detailEntity
import io.github.v1servicenotification.domain.detail.domain.repository.vo.QDetailVO
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class DetailRepositoryImpl(
    private val query: JPAQueryFactory,
) : DetailRepositorySpi {
    override fun findAllByUserId(userId: UUID): List<DetailModel> {
        return query
            .select(
                QDetailVO(
                    detailEntity.id,
                    detailEntity.title,
                    detailEntity.content,
                    detailEntity.sentAt,
                    detailEntity.isRead,
                    detailEntity.userId,
                    categoryEntity.name,
                    categoryEntity.destination
                )
            )
            .join(detailEntity.categoryEntity, categoryEntity)
            .fetch()
            .map {
                DetailModel(it.id, it.title, it.content, it.sentAt, it.isRead, it.userId, it.name, it.destination)
            }
            .toList()
    }
}