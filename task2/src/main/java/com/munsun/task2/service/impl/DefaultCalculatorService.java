package com.munsun.task2.service.impl;

import com.munsun.task2.model.Credit;
import com.munsun.task2.model.PaymentSchedule;
import com.munsun.task2.serialize.Serializator;
import com.munsun.task2.serialize.impl.CreditSerializator;
import com.munsun.task2.serialize.impl.PaymentScheduleSerializator;
import com.munsun.task2.service.CalculatorService;
import com.munsun.task2.service.impl.providers.CreditCalculator;
import com.munsun.task2.service.impl.providers.impl.DefaultCreditCalculator;
import jakarta.xml.bind.JAXBException;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.InputStream;

public class DefaultCalculatorService implements CalculatorService {
    private final CreditCalculator creditCalculator;
    private final Serializator<Credit> creditSerializator;
    private final Serializator<PaymentSchedule> paymentScheduleSerializator;

    public DefaultCalculatorService() {
        this.creditCalculator = new DefaultCreditCalculator();
        this.creditSerializator = new CreditSerializator();
        this.paymentScheduleSerializator = new PaymentScheduleSerializator();
    }

    public DefaultCalculatorService(CreditCalculator creditCalculator, Serializator<Credit> creditSerializator, Serializator<PaymentSchedule> paymentScheduleSerializator) {
        this.creditCalculator = creditCalculator;
        this.creditSerializator = creditSerializator;
        this.paymentScheduleSerializator = paymentScheduleSerializator;
    }

    @Override
    public InputStream calculateCredit(InputStream inputStream) throws JAXBException, DatatypeConfigurationException {
        Credit credit = creditSerializator.deserialize(inputStream);
        PaymentSchedule paymentSchedule = null;
        switch (credit.getType()) {
            case ANNUIETY:
                paymentSchedule = creditCalculator.calculateWithAnnuietyPayments(credit);
                break;
            case DIFFERENT:
                paymentSchedule = creditCalculator.calculateWithDifferentPayments(credit);
                break;
            default:
                throw new IllegalArgumentException("Unsupported type of credit!");
        }
        return paymentScheduleSerializator.serialize(paymentSchedule);
    }
}
