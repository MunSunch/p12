package com.munsun.task2.serialize.impl;

import com.munsun.task2.model.Credit;
import com.munsun.task2.serialize.AbstractSerializer;
import jakarta.xml.bind.JAXBException;
import java.io.*;

public class CreditSerializator extends AbstractSerializer<Credit> {
    public CreditSerializator() {
        super(Credit.class, "/schema/input.xsd");
    }

    @Override
    public Credit deserialize(InputStream inputStream) throws JAXBException {
        return (Credit) getUnmarshaller().unmarshal(inputStream);
    }

    @Override
    public InputStream serialize(Credit credit) throws JAXBException {
        StringWriter writer = new StringWriter();
        getMarshaller().marshal(credit, writer);
        return new BufferedInputStream(new ByteArrayInputStream(writer.toString().getBytes()));
    }
}