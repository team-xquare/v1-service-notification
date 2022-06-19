package io.github.v1servicenotification.infrastructure.feign

import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder
import io.github.v1servicenotification.infrastructure.feign.error.OtherBadRequestException
import io.github.v1servicenotification.infrastructure.feign.error.OtherExpiredTokenException
import io.github.v1servicenotification.infrastructure.feign.error.OtherForbiddenException
import io.github.v1servicenotification.infrastructure.feign.error.OtherUnAuthorizedException

class FeignClientErrorDecoder: ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception {
        if (response.status() >= 400) {
            when (response.status()) {
                401 -> throw OtherUnAuthorizedException.EXCEPTION
                403 -> throw OtherForbiddenException.EXCEPTION
                419 -> throw OtherExpiredTokenException.EXCEPTION
                else -> throw OtherBadRequestException.EXCEPTION
            }
        }

        return FeignException.errorStatus(methodKey, response)
    }

}