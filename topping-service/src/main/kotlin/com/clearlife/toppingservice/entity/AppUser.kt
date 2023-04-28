package com.clearlife.toppingservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("app_users")
data class AppUser(
        @Id
        val id: Long,
        val username: String,
        val password: String,
        val role: String,
        val email: String,
        val created_at: LocalDateTime
)