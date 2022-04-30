package io.github.v1servicenotification.domain.category.domain.repository

import io.github.v1servicenotification.domain.category.domain.CategoryEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface NotificationCategoryRepository : CrudRepository<CategoryEntity, UUID> {
    fun findAllByDefaultActivatedIsTrue(): List<CategoryEntity>
}