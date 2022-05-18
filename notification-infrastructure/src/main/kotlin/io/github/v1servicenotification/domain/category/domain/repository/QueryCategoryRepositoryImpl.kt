package io.github.v1servicenotification.domain.category.domain.repository

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.queryCategory.spi.QueryCategoryRepositorySpi
import io.github.v1servicenotification.domain.category.exception.CategoryNotFoundException
import io.github.v1servicenotification.domain.category.mapper.CategoryMapper
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class QueryCategoryRepositoryImpl(
    private val categoryMapper: CategoryMapper,
    private val categoryRepository: CategoryRepository
) : QueryCategoryRepositorySpi {

    override fun findById(id: UUID): Category {
        val category = categoryRepository.findById(id)
            .orElseThrow {
                CategoryNotFoundException.EXCEPTION
            }

        return categoryMapper.categoryEntityToDomain(category)
    }

    override fun findAllByDefaultActivatedIsTrue(): List<Category> {
        return categoryRepository.findAllByDefaultActivatedIsTrue()
            .stream()
            .map { categoryMapper.categoryEntityToDomain(it) }
            .toList()
    }
}