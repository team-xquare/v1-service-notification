package io.github.v1servicenotification.domain.category.domain.repository

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.updateCategory.spi.UpdateCategoryRepositorySpi
import io.github.v1servicenotification.domain.category.exception.CategoryNotFoundException
import io.github.v1servicenotification.domain.category.mapper.CategoryMapper
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

    override fun removeCategory(categoryId: UUID) {
        categoryRepository.findById(categoryId)
            .orElseThrow {
                CategoryNotFoundException.EXCEPTION
            }
    }

}