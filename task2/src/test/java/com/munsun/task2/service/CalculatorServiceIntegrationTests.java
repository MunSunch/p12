package com.munsun.task2.service;

import com.munsun.task2.TestUtils;
import com.munsun.task2.service.impl.DefaultCalculatorService;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class CalculatorServiceIntegrationTests {
    private CalculatorService calculatorService = new DefaultCalculatorService();

    @DisplayName("Test calculate Credit with Annuiety Type")
    @Test
    public void givenCreditWithAnnuietyType_whenCalculate_thenPaymentSchedule() throws DatatypeConfigurationException, JAXBException {
        String testCreditXML = TestUtils.getTestCreditWithAnnuietyType_toXML_var1();
        String expectedPaymentScheduleXML = TestUtils.getTestPaymentScheduleXML_var1();

        try(var stream = calculatorService.calculateCredit(new BufferedInputStream(new ByteArrayInputStream(testCreditXML.getBytes())))) {
            var actualPaymentScheduleXML = new String(stream.readAllBytes());
            assertThat(actualPaymentScheduleXML)
                    .isNotBlank()
                    .isEqualTo(expectedPaymentScheduleXML);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @DisplayName("Test calculate Credit with different Type")
    @Test
    public void givenCreditWithDifferentType_whenCalculate_thenPaymentSchedule() throws DatatypeConfigurationException, JAXBException {
        String testCreditXML = TestUtils.getTestCreditWithDifferentType_toXML_var1();
        String expectedPaymentScheduleXML = TestUtils.getTestPaymentScheduleDifferentPaymentsXML_var1();

        try(var stream = calculatorService.calculateCredit(new BufferedInputStream(new ByteArrayInputStream(testCreditXML.getBytes())))) {
            var actualPaymentScheduleXML = new String(stream.readAllBytes());
            assertThat(actualPaymentScheduleXML)
                    .isNotBlank()
                    .isEqualTo(expectedPaymentScheduleXML);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
