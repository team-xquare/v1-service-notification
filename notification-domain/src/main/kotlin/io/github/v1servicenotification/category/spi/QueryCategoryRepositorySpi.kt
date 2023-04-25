package io.github.v1servicenotification.category.spi

import io.github.v1servicenotification.annotation.Spi
import io.github.v1servicenotification.category.Category

@Spi
interface QueryCategoryRepositorySpi {
    fun existByTopic(topic: String): Boolean
    fun findByTopic(topic: String): Category
    fun findAllByDefaultActivated(defaultActivated: Boolean): List<Category>
}
