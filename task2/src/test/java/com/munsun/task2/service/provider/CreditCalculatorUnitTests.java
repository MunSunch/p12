package com.munsun.task2.service.provider;

import com.munsun.task2.TestUtils;
import com.munsun.task2.model.Credit;
import com.munsun.task2.model.PaymentSchedule;
import com.munsun.task2.model.PaymentScheduleElement;
import com.munsun.task2.service.impl.providers.CreditCalculator;
import com.munsun.task2.service.impl.providers.impl.DefaultCreditCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.datatype.DatatypeConfigurationException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class CreditCalculatorUnitTests {
    private final CreditCalculator creditCalculator = new DefaultCreditCalculator();

    @DisplayName("Test return payment schedule with annuity payments")
    @Test
    public void givenCreditWithAnnuityPayments_whenCalculateCredit_thenReturnPaymentScheduleWithAnnuityPayments() throws DatatypeConfigurationException {
        Credit credit = TestUtils.getTestCreditWithAnnuietyType_var1();
        BigDecimal expectedRemainingDebtLastPayment = new BigDecimal("0.00");
        int expectedNumberOfPayments = credit.getTerm();

        PaymentSchedule actualSchedule = creditCalculator.calculateWithAnnuietyPayments(credit);

        assertThat(actualSchedule.getPayments().getPayment())
                .isNotNull()
                .hasSize(expectedNumberOfPayments);
        assertThat(actualSchedule.getPayments().getPayment().getLast())
                .extracting(PaymentScheduleElement::getRemainingDebt)
                .isEqualTo(expectedRemainingDebtLastPayment);

        PaymentScheduleElement firstPayment = actualSchedule.getPayments().getPayment().getFirst();
        assertThat(actualSchedule.getPayments().getPayment())
                .allMatch(x -> x.getTotalPayment().equals(firstPayment.getTotalPayment()));
    }

    @DisplayName("Test return payment schedule with different payments")
    @Test
    public void givenCreditWithDifferentPayments_whenCalculateCredit_thenReturnPaymentScheduleWithDifferentPayments() throws DatatypeConfigurationException {
        Credit credit = TestUtils.getTestCreditWithAnnuietyType_var1();
        BigDecimal expectedRemainingDebtLastPayment = new BigDecimal("0.00");
        int expectedNumberOfPayments = credit.getTerm();

        PaymentSchedule actualSchedule = creditCalculator.calculateWithDifferentPayments(credit);

        assertThat(actualSchedule.getPayments().getPayment())
                .isNotNull()
                .hasSize(expectedNumberOfPayments);
        assertThat(actualSchedule.getPayments().getPayment().getLast())
                .extracting(PaymentScheduleElement::getRemainingDebt)
                .isEqualTo(expectedRemainingDebtLastPayment);

        PaymentScheduleElement firstPayment = actualSchedule.getPayments().getPayment().getFirst();
        assertThat(actualSchedule.getPayments().getPayment())
                .allMatch(x -> x.getDebtPayment().equals(firstPayment.getDebtPayment()));
    }
}