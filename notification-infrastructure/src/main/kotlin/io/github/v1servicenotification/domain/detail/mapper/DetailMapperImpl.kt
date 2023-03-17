package io.github.v1servicenotification.domain.detail.mapper

import io.github.v1servicenotification.detail.Detail
import io.github.v1servicenotification.domain.category.domain.repository.CategoryRepository
import io.github.v1servicenotification.domain.category.exception.CategoryNotFoundException
import io.github.v1servicenotification.domain.detail.domain.DetailEntity
import org.springframework.stereotype.Component

@Component
class DetailMapperImpl(
     private val categoryRepository: CategoryRepository
) : DetailMapper {
    override fun detailDomainToEntity(detail: Detail): DetailEntity {
        val categoryEntity = categoryRepository.findById(detail.categoryId)
            .orElseThrow {
                CategoryNotFoundException.EXCEPTION
            }

        return DetailEntity(
            id = detail.id,
            title = detail.title,
            content = detail.content,
            sentAt = detail.sentAt,
            isRead = detail.isRead,
            userId = detail.userId,
            categoryEntity = categoryEntity
        )
    }

    override fun detailEntityToDomain(detailEntity: DetailEntity): Detail {
        return Detail(
            id = detailEntity.id,
            title = detailEntity.title,
            content = detailEntity.content,
            sentAt = detailEntity.sentAt,
            isRead = detailEntity.isRead,
            userId = detailEntity.userId,
            categoryId = detailEntity.getCategoryId()
        )
    }
}
