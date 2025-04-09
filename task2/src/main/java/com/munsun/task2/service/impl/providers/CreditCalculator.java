package com.munsun.task2.service.impl.providers;

import com.munsun.task2.model.Credit;
import com.munsun.task2.model.PaymentSchedule;

import javax.xml.datatype.DatatypeConfigurationException;
import java.math.BigDecimal;

public interface CreditCalculator {
    PaymentSchedule calculateWithAnnuietyPayments(Credit credit) throws DatatypeConfigurationException;
    PaymentSchedule calculateWithDifferentPayments(Credit credit) throws DatatypeConfigurationException;
}