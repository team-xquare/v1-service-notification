package io.github.v1servicenotification.domain.category.domain.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.category.spi.QueryCategoryRepositorySpi
import io.github.v1servicenotification.category.spi.UpdateCategoryRepositorySpi
import io.github.v1servicenotification.domain.category.domain.QCategoryEntity.categoryEntity
import io.github.v1servicenotification.domain.category.exception.CategoryNotFoundException
import io.github.v1servicenotification.domain.category.mapper.CategoryMapper
import io.github.v1servicenotification.global.extension.findOne
import io.github.v1servicenotification.setting.spi.SettingCategorySpi
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class CustomCategoryRepositoryImpl(
    private val categoryMapper: CategoryMapper,
    private val categoryRepository: CategoryRepository,
    private val jpaQueryFactory: JPAQueryFactory,
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

    override fun existByTopic(topic: String): Boolean {
        return categoryRepository.existsByTopic(topic)
    }

    override fun findByTopic(topic: String): Category {
        val category = categoryRepository.findByTopic(topic)
            ?: throw CategoryNotFoundException.EXCEPTION

        return categoryMapper.categoryEntityToDomain(category)
    }

    override fun findByStartingWithTopic(topic: String): List<UUID> {
        return jpaQueryFactory
            .select(categoryEntity.id)
            .from(categoryEntity)
            .where(categoryEntity.topic.startsWith(topic))
            .fetch()

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
