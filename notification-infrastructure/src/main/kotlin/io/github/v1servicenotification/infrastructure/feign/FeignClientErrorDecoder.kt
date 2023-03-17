package io.github.v1servicenotification.infrastructure.feign

import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder
import io.github.v1servicenotification.infrastructure.feign.error.FeignBadRequestException
import io.github.v1servicenotification.infrastructure.feign.error.FeignExpiredTokenException
import io.github.v1servicenotification.infrastructure.feign.error.FeignForbiddenException
import io.github.v1servicenotification.infrastructure.feign.error.FeignUnAuthorizedException

class FeignClientErrorDecoder: ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception {
        if (response.status() >= 400) {
            when (response.status()) {
                401 -> throw FeignUnAuthorizedException.EXCEPTION
                403 -> throw FeignForbiddenException.EXCEPTION
                419 -> throw FeignExpiredTokenException.EXCEPTION
                else -> throw FeignBadRequestException.EXCEPTION
            }
        }

        return FeignException.errorStatus(methodKey, response)
    }
}
