package io.github.v1servicenotification.domain.category.presentation.dto.request

import javax.validation.constraints.NotNull
import kotlin.properties.Delegates

class CreateCategoryRequest {

    @NotNull
    lateinit var name: String

    @NotNull
    lateinit var destination: String

    var defaultActivated by Delegates.notNull<Boolean>()

}