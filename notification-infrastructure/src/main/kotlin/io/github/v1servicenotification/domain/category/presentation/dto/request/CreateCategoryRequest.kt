package io.github.v1servicenotification.domain.category.presentation.dto.request

import javax.validation.constraints.NotNull
import kotlin.properties.Delegates

class CreateCategoryRequest {

    @NotNull
    lateinit var name: String
        private set

    @NotNull
    lateinit var destination: String
        private set

    var defaultActivated by Delegates.notNull<Boolean>()
        private set

    @NotNull
    lateinit var categoryImage: String
        private set
}
