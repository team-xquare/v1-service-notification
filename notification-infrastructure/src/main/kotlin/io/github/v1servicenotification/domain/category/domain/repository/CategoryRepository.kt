package io.github.v1servicenotification.domain.category.domain.repository

import io.github.v1servicenotification.domain.category.domain.CategoryEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CategoryRepository : CrudRepository<CategoryEntity, UUID> {
    fun findAllByDefaultActivated(defaultActivated: Boolean): List<CategoryEntity>
    fun existsByTopic(topic: String): Boolean
    fun findByTopic(topic: String): CategoryEntity?
}
