package io.github.v1servicenotification.global.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter: OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (request.getHeader("Request-User-Id") != null && request.getHeader("Request-User-Role") != null && request.getHeader(
                "Request-User-Authorities"
            ) != null
        ) {
            val userId = request.getHeader("Request-User-Id")
            val userRole: UserRole = UserRole.valueOf(request.getHeader("Request-User-Role"))
            val userAuthorities = listOf(request.getHeader("Request-User-Authorities"))
            val authorities: MutableCollection<SimpleGrantedAuthority> = ArrayList()

            for (userAuthority in userAuthorities) {
                authorities.add(SimpleGrantedAuthority(userAuthority))
            }

            authorities.add(SimpleGrantedAuthority(userRole.name))
            val userDetails: UserDetails = User(userId, "", emptyList())
            val authentication: Authentication = UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

}