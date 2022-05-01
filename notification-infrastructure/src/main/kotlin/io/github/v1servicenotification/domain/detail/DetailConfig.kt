package io.github.v1servicenotification.domain.detail

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.detail.Detail
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

@Configuration
@ComponentScan(
    basePackageClasses = [Detail::class],
    includeFilters = [
        ComponentScan.Filter(
            type = FilterType.ANNOTATION,
            value = [DomainService::class]
        )
    ]
)
class DetailConfig