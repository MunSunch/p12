package com.munsun.task2.serialize.impl;

import com.munsun.task2.model.Credit;
import com.munsun.task2.model.PaymentSchedule;
import com.munsun.task2.serialize.AbstractSerializer;
import jakarta.xml.bind.JAXBException;
import java.io.*;

public class PaymentScheduleSerializator extends AbstractSerializer<PaymentSchedule> {
    public PaymentScheduleSerializator() {
        super(PaymentSchedule.class, "/schema/input.xsd");
    }

    @Override
    public PaymentSchedule deserialize(InputStream inputStream) throws JAXBException {
        return (PaymentSchedule) getUnmarshaller().unmarshal(inputStream);
    }

    @Override
    public InputStream serialize(PaymentSchedule paymentSchedule) throws JAXBException {
        StringWriter writer = new StringWriter();
        getMarshaller().marshal(paymentSchedule, writer);
        return new BufferedInputStream(new ByteArrayInputStream(writer.toString().getBytes()));
    }
}
