package io.github.v1servicenotification.global.extension

import org.springframework.data.repository.CrudRepository

fun <T, ID> CrudRepository<T, ID>.findOne(id: ID): T? = findById(id).orElse(null)