package io.github.v1servicenotification.domain.category.mapper

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.domain.category.domain.CategoryEntity
import org.springframework.stereotype.Component

@Component
class CategoryMapperImpl: CategoryMapper {

    override fun categoryDomainToEntity(category: Category): CategoryEntity {
        return CategoryEntity(
            id = category.id,
            title = category.title,
            destination = category.destination,
            defaultActivated = category.defaultActivated,
            topic = category.topic,
        )
    }

    override fun categoryEntityToDomain(categoryEntity: CategoryEntity): Category {
        return Category(
            id = categoryEntity.id,
            title = categoryEntity.title,
            destination = categoryEntity.destination,
            defaultActivated = categoryEntity.defaultActivated,
            topic = categoryEntity.topic,
        )
    }
}