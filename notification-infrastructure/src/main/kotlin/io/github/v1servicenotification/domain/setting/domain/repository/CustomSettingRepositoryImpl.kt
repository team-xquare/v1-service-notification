package io.github.v1servicenotification.domain.setting.domain.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.detail.spi.PostDetailSettingRepositorySpi
import io.github.v1servicenotification.domain.category.domain.QCategoryEntity.categoryEntity
import io.github.v1servicenotification.domain.category.mapper.CategoryMapper
import io.github.v1servicenotification.domain.setting.domain.QSettingEntity.settingEntity
import io.github.v1servicenotification.domain.setting.domain.SettingEntity
import io.github.v1servicenotification.domain.setting.domain.SettingId
import io.github.v1servicenotification.domain.setting.mapper.SettingMapper
import io.github.v1servicenotification.setting.Setting
import io.github.v1servicenotification.setting.spi.SettingRepositorySpi
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class CustomSettingRepositoryImpl(
    private val settingRepository: SettingRepository,
    private val settingMapper: SettingMapper,
    private val categoryMapper: CategoryMapper,
    private val jpaQueryFactory: JPAQueryFactory
) : SettingRepositorySpi, PostDetailSettingRepositorySpi {

    override fun saveAllSetting(categories: List<Category>, userId: UUID, isActivated: Boolean): List<Setting> {
        val settingEntities = categories.map { category ->
            SettingEntity(
                settingId = SettingId(userId, categoryMapper.categoryDomainToEntity(category)),
                isActivated = isActivated
            )
        }
        val savedSettingEntities = settingRepository.saveAll(settingEntities)
        return savedSettingEntities.map { settingMapper.settingEntityToDomain(it) }
    }


    override fun updateAllSetting(categories: List<Category>, userId: UUID, isActivated: Boolean): List<Setting> {
        val settingEntities = categories.map { category ->
            SettingEntity(
                settingId = SettingId(userId, categoryMapper.categoryDomainToEntity(category)),
                isActivated = isActivated
            )
        }
        val savedSettingEntities = settingRepository.saveAll(settingEntities)
        return savedSettingEntities.map { settingMapper.settingEntityToDomain(it) }
    }

    override fun settingExist(categories: List<Category>, userId: UUID): Boolean {
        return getSettingIdList(categories, userId).map { settingId ->
            settingRepository.existsById(settingId)
        }.all { it }
    }

    private fun getSettingIdList(categories: List<Category>, userId: UUID): List<SettingId> {
        return categories.map { category ->
            SettingId(
                userId = userId,
                categoryEntity = categoryMapper.categoryDomainToEntity(category)
            )
        }
    }

    override fun queryActivatedCategory(userId: UUID): List<Category> {
        return jpaQueryFactory
            .select(categoryEntity)
            .from(categoryEntity)
            .leftJoin(categoryEntity.settingList, settingEntity)
            .on(settingEntity.settingId.userId.eq(userId))
            .where(
                settingEntity.isActivated.isTrue
                    .or(
                        categoryEntity.defaultActivated.isTrue
                            .and(settingEntity.isActivated.isNull)
                    )
            )
            .fetch()
            .map {
                categoryMapper.categoryEntityToDomain(it)
            }
    }

    override fun findAllUserIdByTopicAndIsActivated(topic: String, isActivated: Boolean): List<UUID> {
        return jpaQueryFactory
            .select(settingEntity.settingId.userId)
            .from(settingEntity)
            .leftJoin(settingEntity.settingId.categoryEntity, categoryEntity)
            .where(
                settingEntity.isActivated.eq(isActivated)
                    .and(categoryEntity.topic.eq(topic))
            )
            .fetch()
    }
}
