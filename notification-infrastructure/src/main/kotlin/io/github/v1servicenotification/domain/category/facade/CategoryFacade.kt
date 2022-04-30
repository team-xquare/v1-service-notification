package io.github.v1servicenotification.domain.category.facade

import io.github.v1servicenotification.domain.category.domain.CategoryEntity
import io.github.v1servicenotification.domain.category.domain.repository.CategoryRepository
import io.github.v1servicenotification.domain.category.exception.CategoryNotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
class CategoryFacade(
    private val notificationCategoryRepository: CategoryRepository
) {

    fun getCategoryById(uuid: UUID): CategoryEntity {
        return notificationCategoryRepository.findById(uuid)
            .orElseThrow {
                CategoryNotFoundException.EXCEPTION
            }
    }

}