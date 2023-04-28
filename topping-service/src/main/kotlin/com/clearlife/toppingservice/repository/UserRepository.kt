package com.clearlife.toppingservice.repository

import com.clearlife.toppingservice.entity.AppUser
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository: ReactiveCrudRepository<AppUser, Long> {
    fun findByUsername(username: String): Mono<AppUser>
}