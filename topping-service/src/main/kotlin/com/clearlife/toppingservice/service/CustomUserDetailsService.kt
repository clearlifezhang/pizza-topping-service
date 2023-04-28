package com.clearlife.toppingservice.service

import com.clearlife.toppingservice.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : ReactiveUserDetailsService {
    override fun findByUsername(username: String): Mono<UserDetails> {
        return userRepository.findByUsername(username)
                .map { appUser ->
                    val authorities = mutableListOf(SimpleGrantedAuthority("ROLE_${appUser.role}"))
                    User.withUsername(appUser.username)
                            .password(appUser.password)
                            .authorities(authorities)
                            .accountExpired(false)
                            .accountLocked(false)
                            .credentialsExpired(false)
                            .disabled(false)
                            .build()
                }
    }
}
