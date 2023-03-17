package io.github.v1servicenotification.domain.category.domain.repository

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.spi.QueryCategoryRepositorySpi
import io.github.v1servicenotification.category.spi.UpdateCategoryRepositorySpi
import io.github.v1servicenotification.domain.category.exception.CategoryNotFoundException
import io.github.v1servicenotification.domain.category.mapper.CategoryMapper
import io.github.v1servicenotification.global.extension.findOne
import io.github.v1servicenotification.setting.spi.SettingCategorySpi
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class CustomCategoryRepositoryImpl(
    private val categoryMapper: CategoryMapper,
    private val categoryRepository: CategoryRepository
) : UpdateCategoryRepositorySpi, QueryCategoryRepositorySpi, SettingCategorySpi {

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

    override fun exist(id: UUID): Boolean {
        return categoryRepository.existsById(id)
    }

    override fun findById(id: UUID): Category {
        val category = categoryRepository.findById(id)
            .orElseThrow {
                CategoryNotFoundException.EXCEPTION
            }

        return categoryMapper.categoryEntityToDomain(category)
    }

    override fun findAllByDefaultActivated(defaultActivated: Boolean): List<Category> {
        return categoryRepository.findAllByDefaultActivated(defaultActivated)
            .stream()
            .map { categoryMapper.categoryEntityToDomain(it) }
            .toList()
    }

    override fun removeCategory(category: Category) {
        categoryRepository.delete(
            categoryMapper.categoryDomainToEntity(category)
        )
    }
}
