package com.munsun.task5.advice;

import com.munsun.task5.dto.response.ErrorResponseDto;
import jakarta.xml.bind.JAXB;

import java.io.StringWriter;
import java.time.LocalDate;

public class ExceptionAdvice {
    public static String handleException(Exception e, Integer statusCode) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(e.getMessage(),
                statusCode.toString(), e.getClass().getSimpleName(), LocalDate.now().toString());
        StringWriter result = new StringWriter();
        JAXB.marshal(errorResponseDto, result);
        return result.toString();
    }
}
