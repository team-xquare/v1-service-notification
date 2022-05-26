package io.github.v1servicenotification.domain.category.domain.repository

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.updateCategory.spi.UpdateCategoryRepositorySpi
import io.github.v1servicenotification.domain.category.mapper.CategoryMapper
import io.github.v1servicenotification.global.extension.findOne
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UpdateCategoryRepositoryImpl(
    private val categoryMapper: CategoryMapper,
    private val categoryRepository: CategoryRepository
) : UpdateCategoryRepositorySpi {

    override fun saveCategory(category: Category) {
        val categoryEntity = categoryMapper.categoryDomainToEntity(category)
        categoryRepository.save(categoryEntity)
    }

    override fun findCategoryById(categoryId: UUID): Category? {
        val category = categoryRepository.findOne(categoryId)

        return category?.let {
            categoryMapper.categoryEntityToDomain(category)
        }
    }

    override fun removeCategory(category: Category) {
        categoryRepository.delete(
            categoryMapper.categoryDomainToEntity(category)
        )
    }

}