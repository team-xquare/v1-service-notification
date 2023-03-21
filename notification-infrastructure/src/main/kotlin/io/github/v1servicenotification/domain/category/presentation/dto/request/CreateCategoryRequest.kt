package io.github.v1servicenotification.domain.category.presentation.dto.request

import io.github.v1servicenotification.category.Topic
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

    lateinit var topic: Topic
        private set
}
