package io.github.v1servicenotification.domain.detail.mapper

import io.github.v1servicenotification.detail.Detail
import io.github.v1servicenotification.domain.detail.domain.DetailEntity
import org.mapstruct.Mapper

@Mapper
interface DetailMapper {
    fun detailDomainToEntity(detail: Detail): DetailEntity
    fun detailEntityToDomain(detailEntity: DetailEntity): Detail
}