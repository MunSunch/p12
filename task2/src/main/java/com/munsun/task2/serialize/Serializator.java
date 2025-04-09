package com.munsun.task2.serialize;

import jakarta.xml.bind.JAXBException;

import java.io.InputStream;
import java.io.OutputStream;

public interface Serializator<T>{
    T deserialize(InputStream inputStream) throws JAXBException;
    InputStream serialize(T paymentSchedule) throws JAXBException;
}
