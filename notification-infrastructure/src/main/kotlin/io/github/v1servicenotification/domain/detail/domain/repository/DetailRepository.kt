package io.github.v1servicenotification.domain.detail.domain.repository

import io.github.v1servicenotification.domain.detail.domain.DetailEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface DetailRepository : CrudRepository<DetailEntity, UUID> {
    fun findAllByUserId(userId: UUID): List<DetailEntity>
    fun findByUserIdAndId(userId: UUID, id: UUID): DetailEntity?
}
