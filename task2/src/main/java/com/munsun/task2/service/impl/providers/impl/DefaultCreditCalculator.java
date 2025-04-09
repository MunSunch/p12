package com.munsun.task2.service.impl.providers.impl;

import com.munsun.task2.model.Credit;
import com.munsun.task2.model.PaymentSchedule;
import com.munsun.task2.model.PaymentScheduleElement;
import com.munsun.task2.properties.ResourceProperties;
import com.munsun.task2.service.impl.providers.CreditCalculator;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DefaultCreditCalculator implements CreditCalculator {
    private final int countDigitAfterPoint = ResourceProperties.getProperty("calculator.count_digit_after_point", Integer.class);

    @Override
    public PaymentSchedule calculateWithAnnuietyPayments(Credit credit) throws DatatypeConfigurationException {
        BigDecimal monthlyRate = getMonthlyRate(credit.getRate());
        BigDecimal totalAmount = credit.getAmount();
        BigDecimal monthlyPayment = getMonthlyPayment(totalAmount, credit.getTerm(), credit.getRate());
        var schedule = new PaymentSchedule();
        var payments = new PaymentSchedule.Payments();
        payments.getPayment().addAll(getSchedule(monthlyPayment, monthlyRate, totalAmount, credit.getTerm()));
        schedule.setPayments(payments);
        return schedule;
    }

    @Override
    public PaymentSchedule calculateWithDifferentPayments(Credit credit) throws DatatypeConfigurationException {
        BigDecimal totalAmount = credit.getAmount();
        BigDecimal ratePercents = getRatePercents(credit.getRate());
        BigDecimal debtPayment = totalAmount.divide(BigDecimal.valueOf(credit.getTerm()), new MathContext(MathContext.DECIMAL128.getPrecision(), RoundingMode.HALF_EVEN));
        var schedule = new PaymentSchedule();
        var payments = new PaymentSchedule.Payments();
        payments.getPayment().addAll(getSchedule(totalAmount, credit.getTerm(), ratePercents, debtPayment));
        schedule.setPayments(payments);
        return schedule;
    }

    private List<PaymentScheduleElement> getSchedule(BigDecimal totalAmount, Integer term, BigDecimal ratePercents, BigDecimal debtPayment) throws DatatypeConfigurationException {
        List<PaymentScheduleElement> schedule = new ArrayList<>();
        BigDecimal remainingDebt = totalAmount;
        for (int i = 1; i <= term; i++) {
            LocalDate currentDate = LocalDate.now().plusMonths(i);
            BigDecimal interestPayment = remainingDebt.multiply(ratePercents)
                    .multiply(BigDecimal.valueOf(currentDate.getDayOfMonth())
                            .divide(BigDecimal.valueOf(currentDate.getDayOfYear()),
                                    new MathContext(MathContext.DECIMAL128.getPrecision(), RoundingMode.HALF_EVEN)));
            BigDecimal monthlyPayment = debtPayment.add(interestPayment);
            remainingDebt = remainingDebt.subtract(debtPayment);

            PaymentScheduleElement payment = new PaymentScheduleElement();
            payment.setNumberPayment(i);
            payment.setPaymentDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(currentDate.toString()));
            payment.setTotalPayment(monthlyPayment.setScale(countDigitAfterPoint, RoundingMode.HALF_EVEN));
            payment.setInterestPayment(interestPayment.setScale(countDigitAfterPoint, RoundingMode.HALF_EVEN));
            payment.setDebtPayment(debtPayment.setScale(countDigitAfterPoint, RoundingMode.HALF_EVEN));
            payment.setRemainingDebt(remainingDebt.setScale(countDigitAfterPoint, RoundingMode.HALF_EVEN));
            schedule.add(payment);
        }
        return schedule;
    }

    private BigDecimal getRatePercents(BigDecimal newRate) {
        return newRate.divide(BigDecimal.valueOf(100));
    }

    private List<PaymentScheduleElement> getSchedule(BigDecimal monthlyPayment, BigDecimal monthlyRate, BigDecimal totalAmount, Integer term) throws DatatypeConfigurationException {
        List<PaymentScheduleElement> schedule = new ArrayList<>();
        BigDecimal remainingDebt = totalAmount;
        for (int i = 1; i <= term; i++) {
            LocalDate month = LocalDate.now().plusMonths(i);
            BigDecimal interestPayment = remainingDebt.multiply(monthlyRate);
            BigDecimal debtPayment = monthlyPayment.subtract(interestPayment);
            remainingDebt = remainingDebt.subtract(debtPayment);

            PaymentScheduleElement payment = new PaymentScheduleElement();
            payment.setNumberPayment(i);
            payment.setPaymentDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(month.toString()));
            payment.setTotalPayment(monthlyPayment.setScale(countDigitAfterPoint, RoundingMode.HALF_EVEN));
            payment.setInterestPayment(interestPayment.setScale(countDigitAfterPoint, RoundingMode.HALF_EVEN));
            payment.setDebtPayment(debtPayment.setScale(countDigitAfterPoint, RoundingMode.HALF_EVEN));
            payment.setRemainingDebt(remainingDebt.setScale(countDigitAfterPoint, RoundingMode.HALF_EVEN));
            schedule.add(payment);
        }
        return schedule;
    }

    private BigDecimal getMonthlyPayment(BigDecimal totalAmount, Integer term, BigDecimal newRate) {
        BigDecimal monthlyPercent = getMonthlyRate(newRate);
        return totalAmount.multiply(
                monthlyPercent.add(
                        monthlyPercent.divide(
                                BigDecimal.ONE.add(monthlyPercent).pow(term).subtract(BigDecimal.ONE),
                                new MathContext(MathContext.DECIMAL128.getPrecision(), RoundingMode.HALF_EVEN)
                        )
                )
        );
    }

    private BigDecimal getMonthlyRate(BigDecimal newRate) {
        return newRate.divide(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(12), new MathContext(MathContext.DECIMAL128.getPrecision(), RoundingMode.HALF_EVEN));
    }
}