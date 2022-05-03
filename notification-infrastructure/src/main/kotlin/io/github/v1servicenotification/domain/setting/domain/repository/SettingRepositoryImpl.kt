package io.github.v1servicenotification.domain.setting.domain.repository

import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.domain.category.mapper.CategoryMapper
import io.github.v1servicenotification.domain.setting.domain.SettingEntity
import io.github.v1servicenotification.domain.setting.domain.SettingId
import io.github.v1servicenotification.domain.setting.mapper.SettingMapper
import io.github.v1servicenotification.setting.Setting
import io.github.v1servicenotification.setting.activeSetting.spi.SettingRepositorySpi
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class SettingRepositoryImpl(
    private val settingRepository: NotificationSettingRepository,
    private val settingMapper: SettingMapper,
    private val categoryMapper: CategoryMapper
) : SettingRepositorySpi {
    override fun saveSetting(category: Category, userId: UUID, isActivated: Boolean): Setting {
        return settingMapper.settingEntityToDomain(
            settingRepository.save(
                SettingEntity(
                    settingId = getSettingId(category, userId),
                    isActivated = isActivated
                )
            )
        )
    }

    override fun updateSetting(category: Category, userId: UUID, isActivated: Boolean): Setting {
        return settingMapper.settingEntityToDomain(
            settingRepository.save(
                SettingEntity(
                    settingId = getSettingId(category, userId),
                    isActivated = isActivated
                )
            )
        )
    }

    override fun settingExist(category: Category, userId: UUID): Boolean {
        return settingRepository.existsById(getSettingId(category, userId))
    }

    override fun queryActivatedCategory(userId: UUID): List<Category> {
        return settingRepository
            .findBySettingIdUserIdAndActivatedIsTrue(userId)
            .map {
                categoryMapper.categoryEntityToDomain(it.settingId.categoryEntity)
            }
            .toList()
    }

    private fun getSettingId(category: Category, userId: UUID): SettingId {
        return SettingId(
            userId = userId,
            categoryEntity = categoryMapper.categoryDomainToEntity(category)
        )
    }

}