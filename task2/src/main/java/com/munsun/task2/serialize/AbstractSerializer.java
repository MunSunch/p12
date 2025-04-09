package com.munsun.task2.serialize;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.InputStream;

public abstract class AbstractSerializer<T> {
    private JAXBContext jaxbContext;
    private Schema schema;

    protected AbstractSerializer(Class<T> type, String schemaPath) {
        try {
            this.jaxbContext = JAXBContext.newInstance(type);
            this.schema = createSchema(schemaPath);
        } catch (JAXBException | SAXException e) {
            throw new RuntimeException("Initialization failed", e);
        }
    }

    private Schema createSchema(String schemaPath) throws SAXException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        schemaFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        schemaFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        return schemaFactory.newSchema(new StreamSource(getClass().getResourceAsStream(schemaPath)));
    }

    private final ThreadLocal<Marshaller> marshaller = ThreadLocal.withInitial(() -> {
        try {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            return marshaller;
        } catch (JAXBException e) {
            throw new RuntimeException("Marshaller creation failed", e);
        }
    });

    private final ThreadLocal<Unmarshaller> unmarshaller = ThreadLocal.withInitial(() -> {
        try {
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setSchema(schema);
            return unmarshaller;
        } catch (JAXBException e) {
            throw new RuntimeException("Unmarshaller creation failed", e);
        }
    });

    public abstract T deserialize(InputStream inputStream) throws JAXBException;
    public abstract InputStream serialize(T object) throws JAXBException;

    protected Marshaller getMarshaller() {
        return marshaller.get();
    }

    protected Unmarshaller getUnmarshaller() {
        return unmarshaller.get();
    }

    protected void destroy() {
        marshaller.remove();
        unmarshaller.remove();
    }
}