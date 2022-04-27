package io.github.v1servicenotification.global.error

import io.github.v1servicenotification.global.error.exception.ErrorCode
import io.github.v1servicenotification.global.error.exception.NotificationException
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ErrorHandlingFilter: OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: NotificationException) {
            errorToJson(e.errorCode, response)
        } catch (e: Exception) {
            errorToJson(ErrorCode.INTERNAL_SERVER_ERROR, response)
        }
    }

    private fun errorToJson(errorCode: ErrorCode, response: HttpServletResponse) {
        response.status = errorCode.status;
        response.contentType = "application/json";
        response.writer.write(ErrorResponse(errorCode).toString());
    }

}