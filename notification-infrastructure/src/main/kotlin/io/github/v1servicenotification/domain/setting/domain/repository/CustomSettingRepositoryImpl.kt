package io.github.v1servicenotification.domain.setting.domain.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.detail.postDetail.spi.PostDetailSettingRepositorySpi
import io.github.v1servicenotification.domain.category.mapper.CategoryMapper
import io.github.v1servicenotification.domain.detail.domain.QDetailEntity.detailEntity
import io.github.v1servicenotification.domain.setting.domain.QSettingEntity.settingEntity
import io.github.v1servicenotification.domain.setting.domain.SettingEntity
import io.github.v1servicenotification.domain.setting.domain.SettingId
import io.github.v1servicenotification.domain.setting.mapper.SettingMapper
import io.github.v1servicenotification.setting.Setting
import io.github.v1servicenotification.setting.activeSetting.spi.SettingRepositorySpi
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CustomSettingRepositoryImpl(
    private val settingRepository: SettingRepository,
    private val settingMapper: SettingMapper,
    private val categoryMapper: CategoryMapper,
    private val jpaQueryFactory: JPAQueryFactory
) : SettingRepositorySpi, PostDetailSettingRepositorySpi {
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
            .findBySettingIdUserIdAndIsActivatedIsTrue(userId)
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

    override fun findAllUserIdByCategoryId(categoryId: UUID): List<UUID> {
        return jpaQueryFactory
            .select(settingEntity.settingId.userId)
            .from(settingEntity)
            .where(settingEntity.settingId.categoryEntity.id.eq(categoryId))
            .fetch()
    }

}