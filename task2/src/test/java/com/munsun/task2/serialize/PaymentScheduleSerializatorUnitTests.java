package com.munsun.task2.serialize;

import com.munsun.task2.TestUtils;
import com.munsun.task2.model.PaymentSchedule;
import com.munsun.task2.serialize.impl.PaymentScheduleSerializator;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentScheduleSerializatorUnitTests {
    private final Serializator<PaymentSchedule> paymentScheduleSerializator = new PaymentScheduleSerializator();

    @DisplayName("Test serialize PaymentSchedule to Bytes")
    @Test
    public void givenCreditWithAnnuietyType_whenSerialize_thenCreditBytes() throws JAXBException, DatatypeConfigurationException {
        PaymentSchedule schedule = TestUtils.getTestPaymentSchedule_var2();
        String expectedString = TestUtils.getTestPaymentScheduleXML_var2();

        try(InputStream inputStream = paymentScheduleSerializator.serialize(schedule)) {
            String actualString = new String(inputStream.readAllBytes());
            assertThat(actualString)
                    .isEqualTo(expectedString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
