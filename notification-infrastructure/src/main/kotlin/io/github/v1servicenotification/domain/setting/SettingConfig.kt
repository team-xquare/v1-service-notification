package io.github.v1servicenotification.domain.setting

import io.github.v1servicenotification.annotation.DomainService
import io.github.v1servicenotification.annotation.Facade
import io.github.v1servicenotification.setting.Setting
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

@Configuration
@ComponentScan(
    basePackageClasses = [Setting::class],
    includeFilters = [
        ComponentScan.Filter(
            type = FilterType.ANNOTATION,
            value = [DomainService::class, Facade::class]
        )
    ]
)
class SettingConfig