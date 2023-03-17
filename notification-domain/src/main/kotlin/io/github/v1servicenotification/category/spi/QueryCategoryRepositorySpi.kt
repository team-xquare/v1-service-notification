package io.github.v1servicenotification.category.spi

import io.github.v1servicenotification.annotation.Spi
import io.github.v1servicenotification.category.Category
import java.util.UUID

@Spi
interface QueryCategoryRepositorySpi {
    fun exist(id: UUID): Boolean
    fun findById(id: UUID): Category
    fun findAllByDefaultActivated(defaultActivated: Boolean): List<Category>
}
