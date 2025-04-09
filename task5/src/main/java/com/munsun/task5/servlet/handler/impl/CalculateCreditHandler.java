package com.munsun.task5.servlet.handler.impl;

import com.munsun.task2.service.CalculatorService;
import com.munsun.task2.service.impl.DefaultCalculatorService;
import com.munsun.task5.advice.ExceptionAdvice;
import com.munsun.task5.dto.utils.ResponseEntity;
import com.munsun.task5.servlet.handler.Handler;
import com.munsun.task5.utils.enums.ContentType;
import com.munsun.task5.utils.enums.HttpMethod;
import com.munsun.task5.utils.enums.HttpStatusCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.UnmarshalException;
import org.xml.sax.SAXParseException;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;

public class CalculateCreditHandler extends Handler {
    private CalculatorService calculatorService;

    public CalculateCreditHandler() {
        super(HttpMethod.POST, ContentType.XML, "/api/v1/calculate");
        this.calculatorService = new DefaultCalculatorService();
    }

    @Override
    public ResponseEntity handle(HttpServletRequest req) throws IOException {
        try {
            try (var stream = calculatorService.calculateCredit(req.getInputStream())) {
                return new ResponseEntity(HttpStatusCode.OK, new String(stream.readAllBytes()));
            }
        } catch (DatatypeConfigurationException | JAXBException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatusCode.BAD_REQUEST, ExceptionAdvice.handleException(e, HttpStatusCode.BAD_REQUEST.getStatusCode()));
        }
    }
}
