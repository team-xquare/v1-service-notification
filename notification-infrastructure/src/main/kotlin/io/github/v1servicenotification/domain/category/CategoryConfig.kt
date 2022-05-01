package io.github.v1servicenotification.domain.category

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.category.Category
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

@Configuration
@ComponentScan(
    basePackageClasses = [Category::class],
    includeFilters = [
        ComponentScan.Filter(
            type = FilterType.ANNOTATION,
            value = [DomainService::class]
        )
    ]
)
class CategoryConfig