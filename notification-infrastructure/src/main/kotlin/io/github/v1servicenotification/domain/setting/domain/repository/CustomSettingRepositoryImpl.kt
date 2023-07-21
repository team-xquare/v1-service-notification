package io.github.v1servicenotification.domain.setting.domain.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import io.github.v1servicenotification.category.Category
import io.github.v1servicenotification.detail.spi.PostDetailSettingRepositorySpi
import io.github.v1servicenotification.domain.category.domain.CategoryEntity
import io.github.v1servicenotification.domain.category.domain.QCategoryEntity.categoryEntity
import io.github.v1servicenotification.domain.category.domain.repository.CategoryRepository
import io.github.v1servicenotification.domain.category.mapper.CategoryMapper
import io.github.v1servicenotification.domain.setting.domain.QSettingEntity.settingEntity
import io.github.v1servicenotification.domain.setting.domain.SettingId
import io.github.v1servicenotification.domain.setting.mapper.SettingMapper
import io.github.v1servicenotification.setting.Setting
import io.github.v1servicenotification.setting.spi.SettingRepositorySpi
import org.springframework.stereotype.Repository
import java.util.UUID
import javax.transaction.Transactional

@Repository
class CustomSettingRepositoryImpl(
    private val settingRepository: SettingRepository,
    private val categoryMapper: CategoryMapper,
    private val jpaQueryFactory: JPAQueryFactory,
    private val categoryRepository: CategoryRepository,
    private val settingMapper: SettingMapper
) : SettingRepositorySpi, PostDetailSettingRepositorySpi {

    @Transactional
    override fun updateAllSetting(categoryIds: List<UUID>, userId: UUID, isActivated: Boolean) {
        categoryIds.forEach {
            jpaQueryFactory
                .update(settingEntity)
                .set(settingEntity.isActivated, isActivated)
                .where(
                    settingEntity.settingId.categoryEntity.id.eq(it)
                        .and(settingEntity.settingId.userId.eq(userId))
                )
                .execute()
        }
    }

    override fun settingExist(categoryIds: List<UUID>, userId: UUID): Boolean {
        return getSettingIdList(categoryIds, userId).map { settingId ->
            settingRepository.existsById(settingId)
        }.all { it }
    }

    private fun getSettingIdList(categoryIds: List<UUID>, userId: UUID): List<SettingId> {
        return getCategoryById(categoryIds).map { category ->
            SettingId(
                userId = userId,
                categoryEntity = categoryMapper.categoryDomainToEntity(category)
            )
        }
    }

    private fun getCategoryById(categoryId: List<UUID>): List<Category> {
        return categoryRepository.findAllById(categoryId)
            .map { categoryMapper.categoryEntityToDomain(it) }
    }


    override fun queryUserIdSetting(userId: UUID): List<Setting> {
        return jpaQueryFactory
            .selectFrom(settingEntity)
            .where(settingEntity.settingId.userId.eq(userId))
            .fetch()
            .map {
                settingMapper.settingEntityToDomain(it)
            }
    }

    override fun queryUserCategory(userId: UUID): List<Category> {
        return jpaQueryFactory
            .selectFrom(categoryEntity)
            .leftJoin(categoryEntity.settingList, settingEntity)
            .where(settingEntity.settingId.userId.eq(userId))
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

    override fun findIsActivatedByUserIdAndTopic(userId: UUID, topic: String): Boolean {
        return jpaQueryFactory
            .select(settingEntity.isActivated)
            .from(settingEntity)
            .leftJoin(settingEntity.settingId.categoryEntity, categoryEntity)
            .where(
                settingEntity.settingId.userId.eq(userId)
                    .and(categoryEntity.topic.eq(topic))
            )
            .fetchFirst()
    }
}
