package io.github.v1servicenotification.setting.activeSetting.service

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.api.response.CategoryElement
import io.github.v1servicenotification.category.api.response.CategoryListResponse
import io.github.v1servicenotification.setting.activeSetting.api.QueryActivatedCategory
import io.github.v1servicenotification.setting.activeSetting.spi.SettingRepositorySpi
import java.util.*

@DomainService
class QueryActivatedCategoryImpl(
    private val settingRepositorySpi: SettingRepositorySpi
) : QueryActivatedCategory {
    override fun queryActivatedCategory(userId: UUID): CategoryListResponse {
        return CategoryListResponse(
            settingRepositorySpi.queryActivatedCategory(userId)
                .map {
                    CategoryElement(
                        id = it.id,
                        name = it.name,
                        destination = it.destination
                    )
                }
                .toList()
        )
    }
}