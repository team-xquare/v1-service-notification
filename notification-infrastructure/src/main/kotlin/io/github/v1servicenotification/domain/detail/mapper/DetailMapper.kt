package io.github.v1servicenotification.domain.detail.mapper

import io.github.v1servicenotification.detail.Detail
import io.github.v1servicenotification.domain.detail.domain.DetailEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface DetailMapper {
    fun detailDomainToEntity(detail: Detail): DetailEntity

    @Mappings(
        Mapping(source = "categoryEntity.id", target = "categoryId"),
        Mapping(source = "read", target = "isRead")
    )
    fun detailEntityToDomain(detailEntity: DetailEntity): Detail
}