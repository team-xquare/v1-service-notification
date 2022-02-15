package io.github.v1servicenotification.domain.category.domain

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
class NotificationCategory(
        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(columnDefinition = "BINARY(16)")
        val id: UUID,

        @field:Size(max = 20)
        @field:NotNull
        val name: String,

        @field:NotNull
        val destination: String,

        @Column(columnDefinition = "TINYINT(1)")
        @field:NotNull
        val defaultActivated: Boolean
)