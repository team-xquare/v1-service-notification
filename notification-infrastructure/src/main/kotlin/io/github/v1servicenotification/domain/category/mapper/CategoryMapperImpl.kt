package io.github.v1servicenotification.domain.category.mapper

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.domain.category.domain.CategoryEntity
import org.springframework.stereotype.Component

@Component
class CategoryMapperImpl: CategoryMapper {

    override fun categoryDomainToEntity(category: Category): CategoryEntity {
        return CategoryEntity(
            id = category.id,
            name = category.name,
            destination = category.destination,
            defaultActivated = category.defaultActivated,
        )
    }

    override fun categoryEntityToDomain(categoryEntity: CategoryEntity): Category {
        return Category(
            id = categoryEntity.id,
            name = categoryEntity.name,
            destination = categoryEntity.destination,
            defaultActivated = categoryEntity.defaultActivated,
        )
    }
}