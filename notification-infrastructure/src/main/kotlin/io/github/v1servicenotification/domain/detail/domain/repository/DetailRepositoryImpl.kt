package io.github.v1servicenotification.domain.detail.domain.repository

import io.github.v1servicenotification.detail.Detail
import io.github.v1servicenotification.detail.queryDetail.spi.DetailRepositorySpi
import io.github.v1servicenotification.domain.detail.mapper.DetailMapper
import java.util.*

class DetailRepositoryImpl(
    private val detailRepository: DetailRepository,
    private val detailMapper: DetailMapper
): DetailRepositorySpi {
    override fun findAllByUserId(userId: UUID): List<Detail> {
        return detailRepository.findAllByUserId(userId)
            .map { detailMapper.detailEntityToDomain(it) }
            .toList()
    }
}