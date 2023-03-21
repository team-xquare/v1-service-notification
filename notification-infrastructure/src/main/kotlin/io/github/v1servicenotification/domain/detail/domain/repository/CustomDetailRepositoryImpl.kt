package io.github.v1servicenotification.domain.detail.domain.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import io.github.v1servicenotification.detail.Detail
import io.github.v1servicenotification.detail.spi.PostDetailRepositorySpi
import io.github.v1servicenotification.detail.spi.QueryDetailRepositorySpi
import io.github.v1servicenotification.detail.spi.dto.TopicDetailModel
import io.github.v1servicenotification.domain.category.domain.QCategoryEntity.categoryEntity
import io.github.v1servicenotification.domain.detail.domain.QDetailEntity.detailEntity
import io.github.v1servicenotification.domain.detail.domain.repository.vo.QDetailVO
import io.github.v1servicenotification.domain.detail.mapper.DetailMapper
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class CustomDetailRepositoryImpl(
    private val detailRepository: DetailRepository,
    private val detailMapper: DetailMapper,
    private val query: JPAQueryFactory,
) : QueryDetailRepositorySpi, PostDetailRepositorySpi {
    override fun findAllByUserId(userId: UUID): List<TopicDetailModel> {
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
                    categoryEntity.destination,
                    categoryEntity.topic
                )
            )
            .from(detailEntity)
            .where(detailEntity.userId.eq(userId))
            .join(detailEntity.categoryEntity, categoryEntity)
            .fetch()
            .map {
                TopicDetailModel(
                    id = it.id,
                    title = it.title,
                    content = it.content,
                    sentAt = it.sentAt,
                    isRead = it.isRead,
                    userId = it.userId,
                    name = it.name,
                    destination = it.destination,
                    topic = it.topic,
                )
            }
            .toList()
    }

    override fun findAllByUseridAndIsReadFalse(userId: UUID): Int {
        return query
            .selectFrom(detailEntity)
            .where(
                detailEntity.userId.eq(userId),
                detailEntity.isRead.eq(false)
            )
            .fetch().size
    }

    override fun save(detail: Detail) {
        detailRepository.save(
            detailMapper.detailDomainToEntity(detail)
        )
    }

    override fun saveAllDetail(detailList: List<Detail>) {
        val detailEntityList = detailList.stream()
            .map { detailMapper.detailDomainToEntity(it) }
            .toList()

        detailRepository.saveAll(detailEntityList)
    }
}
