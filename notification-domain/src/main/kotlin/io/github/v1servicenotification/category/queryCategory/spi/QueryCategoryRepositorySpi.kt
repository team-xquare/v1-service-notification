package io.github.v1servicenotification.category.queryCategory.spi

import io.github.v1servicenotification.annotation.Spi
import io.github.v1servicenotification.category.Category
import java.util.*

@Spi
interface QueryCategoryRepositorySpi {
    fun exist(id: UUID): Boolean
    fun findById(id: UUID): Category
    fun findAllByDefaultActivatedIsTrue(): List<Category>
}