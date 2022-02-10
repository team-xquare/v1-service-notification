package io.github.v1servicenotification.domain.category.domain

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class NotificationCategory(
        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(columnDefinition = "BINARY(16)")
        val id: UUID,

        @Column(length = 20, nullable = false)
        val name: String,

        @Column(nullable = false)
        val destination: String,

        @Column(columnDefinition = "TINYINT(1)", nullable = false)
        val defaultActivated: Boolean
        )