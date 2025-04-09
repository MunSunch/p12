package com.munsun.task2.serialize.impl;

import com.munsun.task2.model.Credit;
import com.munsun.task2.model.PaymentSchedule;
import com.munsun.task2.serialize.Serializator;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;

public class PaymentScheduleSerializator implements Serializator<PaymentSchedule> {
    private static final JAXBContext jaxbContext;

    static {
        try {
            jaxbContext = JAXBContext.newInstance(PaymentSchedule.class);
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to initialize JAXBContext", e);
        }
    }

    private final ThreadLocal<Marshaller> marshallerThreadLocal = ThreadLocal.withInitial(() -> {
        try {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            return marshaller;
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to create Marshaller", e);
        }
    });

    private final ThreadLocal<Unmarshaller> unmarshallerThreadLocal = ThreadLocal.withInitial(() -> {
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new StreamSource(CreditSerializator.class.getResourceAsStream("/schema/input.xsd")));
            var unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setSchema(schema);
            return unmarshaller;
        } catch (SAXException | JAXBException e) {
            throw new RuntimeException("Failed to create Unmarshaller", e);
        }
    });


    @Override
    public PaymentSchedule deserialize(InputStream inputStream) throws JAXBException {
        return (PaymentSchedule) unmarshallerThreadLocal.get().unmarshal(inputStream);
    }

    @Override
    public InputStream serialize(PaymentSchedule paymentSchedule) throws JAXBException {
        StringWriter writer = new StringWriter();
        marshallerThreadLocal.get().marshal(paymentSchedule, writer);
        return new BufferedInputStream(new ByteArrayInputStream(writer.toString().getBytes()));
    }
}
