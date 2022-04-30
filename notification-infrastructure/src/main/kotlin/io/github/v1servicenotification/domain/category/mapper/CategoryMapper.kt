package io.github.v1servicenotification.domain.category.mapper

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.domain.category.domain.CategoryEntity
import org.mapstruct.Mapper

@Mapper
interface CategoryMapper {
    fun categoryDomainToEntity(category: Category): CategoryEntity
    fun categoryEntityToDomain(categoryEntity: CategoryEntity): Category
}