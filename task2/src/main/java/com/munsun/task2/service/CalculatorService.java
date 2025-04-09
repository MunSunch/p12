package com.munsun.task2.service;

import jakarta.xml.bind.JAXBException;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.InputStream;
import java.io.OutputStream;

public interface CalculatorService {
    InputStream calculateCredit(InputStream inputStream) throws JAXBException, DatatypeConfigurationException;
}
