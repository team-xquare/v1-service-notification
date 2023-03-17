package io.github.v1servicenotification.global.extension

import org.springframework.data.repository.CrudRepository
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

fun <T, ID> CrudRepository<T, ID>.findOne(id: ID): T? = findById(id).orElse(null)

fun getUserId(): UUID = UUID.fromString(SecurityContextHolder.getContext().authentication.name)
