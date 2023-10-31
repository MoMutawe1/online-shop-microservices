package com.onlineshop.OrderService.external.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineshop.OrderService.exception.CustomException;
import com.onlineshop.OrderService.external.response.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

// Custom Errors that could happen at the time of calling another service using FeignClient.
@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {

    //  Whatever the error messages we are getting in the FeignClient we can decode and display for requester.
    @Override
    public Exception decode(String s, Response response) {

        ObjectMapper objectMapper = new ObjectMapper();
        log.info("::{}", response.request().url());
        log.info("::{}", response.request().headers());

        try {
            ErrorResponse errorResponse =
                    objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);

            return new CustomException(
                    errorResponse.getErrorMessage(),
                    errorResponse.getErrorCode(),
                    response.status());

        } catch (IOException e) {
            throw new CustomException("Internal Server Error", "INTERNAL_SERVER_ERROR", 500);
        }
    }
}
