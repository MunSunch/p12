package com.munsun.task2;

import com.munsun.task2.model.Credit;
import com.munsun.task2.model.PaymentSchedule;
import com.munsun.task2.model.PaymentScheduleElement;
import com.munsun.task2.model.TypeCredit;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TestUtils {
    public static Credit getTestCreditWithAnnuietyType_var1() throws DatatypeConfigurationException {
        Credit credit = new Credit();
            credit.setAmount(new BigDecimal("25000.0"));
            credit.setRate(new BigDecimal("12.4"));
            credit.setTerm(12);
            credit.setType(TypeCredit.ANNUIETY);
            credit.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalDate.of(2025, 4, 5).toString()));
        return credit;
    }

    public static String getTestCreditWithAnnuietyType_toXML_var1() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><credit type=\"ANNUIETY\"><amount>25000.0</amount><rate>12.4</rate><term>12</term><start_date>2025-04-06</start_date></credit>";
    }

    public static Credit getTestCreditWithDifferentType_var1() throws DatatypeConfigurationException {
        Credit credit = new Credit();
        credit.setAmount(new BigDecimal("44000.0"));
        credit.setRate(new BigDecimal("12.4"));
        credit.setTerm(18);
        credit.setType(TypeCredit.DIFFERENT);
        credit.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalDate.of(2025, 4, 5).toString()));
        return credit;
    }

    public static String getTestCreditWithDifferentType_toXML_var1() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><credit type=\"DIFFERENT\"><amount>44000.0</amount><rate>12.4</rate><term>18</term><start_date>2025-04-05</start_date></credit>";
    }

    public static String getInvalidTestCreditXML_notCreditTypeAttribute() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><credit><amount>44000.0</amount><rate>12.4</rate><term>18</term><start_date>2025-04-05</start_date></credit>";
    }

    public static Object getInvalidTestCreditXML_notCreditRate() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><credit type=\"DIFFERENT\"><amount>44000.0</amount><term>18</term><start_date>2025-04-05</start_date></credit>";
    }

    public static Object getInvalidTestCreditXML_notCreditStartDate() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><credit type=\"DIFFERENT\"><amount>44000.0</amount><rate>12.4</rate><term>18</term></credit>";
    }

    public static Object getInvalidTestCreditXML_notCreditAmount() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><credit type=\"DIFFERENT\"><rate>12.4</rate><term>18</term><start_date>2025-04-05</start_date></credit>";
    }

    public static Object getInvalidTestCreditXML_notCreditTerm() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><credit type=\"DIFFERENT\"><amount>44000.0</amount><rate>12.4</rate><start_date>2025-04-05</start_date></credit>";
    }

    public static PaymentSchedule getTestPaymentSchedule_var2() throws DatatypeConfigurationException {
        PaymentSchedule schedule = new PaymentSchedule();
        PaymentSchedule.Payments payments = new PaymentSchedule.Payments();
        schedule.setPayments(payments);

        DatatypeFactory df = DatatypeFactory.newInstance();

        // Платеж 1
        PaymentScheduleElement payment1 = new PaymentScheduleElement();
        payment1.setNumberPayment(1);
        payment1.setPaymentDate(df.newXMLGregorianCalendar("2025-05-06"));
        payment1.setTotalPayment(new BigDecimal("2230.95"));
        payment1.setInterestPayment(new BigDecimal("147.62"));
        payment1.setDebtPayment(new BigDecimal("2083.33"));
        payment1.setRemainingDebt(new BigDecimal("22916.67"));
        payments.getPayment().add(payment1);

        // Платеж 2
        PaymentScheduleElement payment2 = new PaymentScheduleElement();
        payment2.setNumberPayment(2);
        payment2.setPaymentDate(df.newXMLGregorianCalendar("2025-06-06"));
        payment2.setTotalPayment(new BigDecimal("2191.93"));
        payment2.setInterestPayment(new BigDecimal("108.60"));
        payment2.setDebtPayment(new BigDecimal("2083.33"));
        payment2.setRemainingDebt(new BigDecimal("20833.33"));
        payments.getPayment().add(payment2);

        // Платеж 3
        PaymentScheduleElement payment3 = new PaymentScheduleElement();
        payment3.setNumberPayment(3);
        payment3.setPaymentDate(df.newXMLGregorianCalendar("2025-07-06"));
        payment3.setTotalPayment(new BigDecimal("2166.22"));
        payment3.setInterestPayment(new BigDecimal("82.89"));
        payment3.setDebtPayment(new BigDecimal("2083.33"));
        payment3.setRemainingDebt(new BigDecimal("18750.00"));
        payments.getPayment().add(payment3);

        // Платеж 4
        PaymentScheduleElement payment4 = new PaymentScheduleElement();
        payment4.setNumberPayment(4);
        payment4.setPaymentDate(df.newXMLGregorianCalendar("2025-08-06"));
        payment4.setTotalPayment(new BigDecimal("2147.32"));
        payment4.setInterestPayment(new BigDecimal("63.99"));
        payment4.setDebtPayment(new BigDecimal("2083.33"));
        payment4.setRemainingDebt(new BigDecimal("16666.67"));
        payments.getPayment().add(payment4);

        // Платеж 5
        PaymentScheduleElement payment5 = new PaymentScheduleElement();
        payment5.setNumberPayment(5);
        payment5.setPaymentDate(df.newXMLGregorianCalendar("2025-09-06"));
        payment5.setTotalPayment(new BigDecimal("2133.13"));
        payment5.setInterestPayment(new BigDecimal("49.80"));
        payment5.setDebtPayment(new BigDecimal("2083.33"));
        payment5.setRemainingDebt(new BigDecimal("14583.33"));
        payments.getPayment().add(payment5);

        // Платеж 6
        PaymentScheduleElement payment6 = new PaymentScheduleElement();
        payment6.setNumberPayment(6);
        payment6.setPaymentDate(df.newXMLGregorianCalendar("2025-10-06"));
        payment6.setTotalPayment(new BigDecimal("2122.22"));
        payment6.setInterestPayment(new BigDecimal("38.89"));
        payment6.setDebtPayment(new BigDecimal("2083.33"));
        payment6.setRemainingDebt(new BigDecimal("12500.00"));
        payments.getPayment().add(payment6);

        // Платеж 7
        PaymentScheduleElement payment7 = new PaymentScheduleElement();
        payment7.setNumberPayment(7);
        payment7.setPaymentDate(df.newXMLGregorianCalendar("2025-11-06"));
        payment7.setTotalPayment(new BigDecimal("2113.33"));
        payment7.setInterestPayment(new BigDecimal("30.00"));
        payment7.setDebtPayment(new BigDecimal("2083.33"));
        payment7.setRemainingDebt(new BigDecimal("10416.67"));
        payments.getPayment().add(payment7);

        // Платеж 8
        PaymentScheduleElement payment8 = new PaymentScheduleElement();
        payment8.setNumberPayment(8);
        payment8.setPaymentDate(df.newXMLGregorianCalendar("2025-12-06"));
        payment8.setTotalPayment(new BigDecimal("2106.13"));
        payment8.setInterestPayment(new BigDecimal("22.79"));
        payment8.setDebtPayment(new BigDecimal("2083.33"));
        payment8.setRemainingDebt(new BigDecimal("8333.33"));
        payments.getPayment().add(payment8);

        // Платеж 9
        PaymentScheduleElement payment9 = new PaymentScheduleElement();
        payment9.setNumberPayment(9);
        payment9.setPaymentDate(df.newXMLGregorianCalendar("2026-01-06"));
        payment9.setTotalPayment(new BigDecimal("3116.67"));
        payment9.setInterestPayment(new BigDecimal("1033.33"));
        payment9.setDebtPayment(new BigDecimal("2083.33"));
        payment9.setRemainingDebt(new BigDecimal("6250.00"));
        payments.getPayment().add(payment9);

        // Платеж 10
        PaymentScheduleElement payment10 = new PaymentScheduleElement();
        payment10.setNumberPayment(10);
        payment10.setPaymentDate(df.newXMLGregorianCalendar("2026-02-06"));
        payment10.setTotalPayment(new BigDecimal("2209.01"));
        payment10.setInterestPayment(new BigDecimal("125.68"));
        payment10.setDebtPayment(new BigDecimal("2083.33"));
        payment10.setRemainingDebt(new BigDecimal("4166.67"));
        payments.getPayment().add(payment10);

        // Платеж 11
        PaymentScheduleElement payment11 = new PaymentScheduleElement();
        payment11.setNumberPayment(11);
        payment11.setPaymentDate(df.newXMLGregorianCalendar("2026-03-06"));
        payment11.setTotalPayment(new BigDecimal("2131.03"));
        payment11.setInterestPayment(new BigDecimal("47.69"));
        payment11.setDebtPayment(new BigDecimal("2083.33"));
        payment11.setRemainingDebt(new BigDecimal("2083.33"));
        payments.getPayment().add(payment11);

        // Платеж 12
        PaymentScheduleElement payment12 = new PaymentScheduleElement();
        payment12.setNumberPayment(12);
        payment12.setPaymentDate(df.newXMLGregorianCalendar("2026-04-06"));
        payment12.setTotalPayment(new BigDecimal("2099.48"));
        payment12.setInterestPayment(new BigDecimal("16.15"));
        payment12.setDebtPayment(new BigDecimal("2083.33"));
        payment12.setRemainingDebt(new BigDecimal("0.00"));
        payments.getPayment().add(payment12);

        return schedule;
    }

    public static String getTestPaymentScheduleXML_var1() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><PaymentSchedule><payments><payment><numberPayment>1</numberPayment><paymentDate>2025-05-06</paymentDate><totalPayment>2225.90</totalPayment><interestPayment>258.33</interestPayment><debtPayment>1967.57</debtPayment><remainingDebt>23032.43</remainingDebt></payment><payment><numberPayment>2</numberPayment><paymentDate>2025-06-06</paymentDate><totalPayment>2225.90</totalPayment><interestPayment>238.00</interestPayment><debtPayment>1987.90</debtPayment><remainingDebt>21044.53</remainingDebt></payment><payment><numberPayment>3</numberPayment><paymentDate>2025-07-06</paymentDate><totalPayment>2225.90</totalPayment><interestPayment>217.46</interestPayment><debtPayment>2008.44</debtPayment><remainingDebt>19036.09</remainingDebt></payment><payment><numberPayment>4</numberPayment><paymentDate>2025-08-06</paymentDate><totalPayment>2225.90</totalPayment><interestPayment>196.71</interestPayment><debtPayment>2029.19</debtPayment><remainingDebt>17006.90</remainingDebt></payment><payment><numberPayment>5</numberPayment><paymentDate>2025-09-06</paymentDate><totalPayment>2225.90</totalPayment><interestPayment>175.74</interestPayment><debtPayment>2050.16</debtPayment><remainingDebt>14956.74</remainingDebt></payment><payment><numberPayment>6</numberPayment><paymentDate>2025-10-06</paymentDate><totalPayment>2225.90</totalPayment><interestPayment>154.55</interestPayment><debtPayment>2071.35</debtPayment><remainingDebt>12885.39</remainingDebt></payment><payment><numberPayment>7</numberPayment><paymentDate>2025-11-06</paymentDate><totalPayment>2225.90</totalPayment><interestPayment>133.15</interestPayment><debtPayment>2092.75</debtPayment><remainingDebt>10792.64</remainingDebt></payment><payment><numberPayment>8</numberPayment><paymentDate>2025-12-06</paymentDate><totalPayment>2225.90</totalPayment><interestPayment>111.52</interestPayment><debtPayment>2114.38</debtPayment><remainingDebt>8678.26</remainingDebt></payment><payment><numberPayment>9</numberPayment><paymentDate>2026-01-06</paymentDate><totalPayment>2225.90</totalPayment><interestPayment>89.68</interestPayment><debtPayment>2136.23</debtPayment><remainingDebt>6542.04</remainingDebt></payment><payment><numberPayment>10</numberPayment><paymentDate>2026-02-06</paymentDate><totalPayment>2225.90</totalPayment><interestPayment>67.60</interestPayment><debtPayment>2158.30</debtPayment><remainingDebt>4383.74</remainingDebt></payment><payment><numberPayment>11</numberPayment><paymentDate>2026-03-06</paymentDate><totalPayment>2225.90</totalPayment><interestPayment>45.30</interestPayment><debtPayment>2180.60</debtPayment><remainingDebt>2203.13</remainingDebt></payment><payment><numberPayment>12</numberPayment><paymentDate>2026-04-06</paymentDate><totalPayment>2225.90</totalPayment><interestPayment>22.77</interestPayment><debtPayment>2203.13</debtPayment><remainingDebt>0.00</remainingDebt></payment></payments></PaymentSchedule>";
    }

    public static String getTestPaymentScheduleDifferentPaymentsXML_var1() {
        return"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><PaymentSchedule><payments><payment><numberPayment>1</numberPayment><paymentDate>2025-05-06</paymentDate><totalPayment>2704.25</totalPayment><interestPayment>259.81</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>41555.56</remainingDebt></payment><payment><numberPayment>2</numberPayment><paymentDate>2025-06-06</paymentDate><totalPayment>2641.37</totalPayment><interestPayment>196.93</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>39111.11</remainingDebt></payment><payment><numberPayment>3</numberPayment><paymentDate>2025-07-06</paymentDate><totalPayment>2600.05</totalPayment><interestPayment>155.61</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>36666.67</remainingDebt></payment><payment><numberPayment>4</numberPayment><paymentDate>2025-08-06</paymentDate><totalPayment>2569.58</totalPayment><interestPayment>125.14</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>34222.22</remainingDebt></payment><payment><numberPayment>5</numberPayment><paymentDate>2025-09-06</paymentDate><totalPayment>2546.70</totalPayment><interestPayment>102.25</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>31777.78</remainingDebt></payment><payment><numberPayment>6</numberPayment><paymentDate>2025-10-06</paymentDate><totalPayment>2529.19</totalPayment><interestPayment>84.74</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>29333.33</remainingDebt></payment><payment><numberPayment>7</numberPayment><paymentDate>2025-11-06</paymentDate><totalPayment>2514.84</totalPayment><interestPayment>70.40</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>26888.89</remainingDebt></payment><payment><numberPayment>8</numberPayment><paymentDate>2025-12-06</paymentDate><totalPayment>2503.28</totalPayment><interestPayment>58.84</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>24444.44</remainingDebt></payment><payment><numberPayment>9</numberPayment><paymentDate>2026-01-06</paymentDate><totalPayment>5475.56</totalPayment><interestPayment>3031.11</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>22000.00</remainingDebt></payment><payment><numberPayment>10</numberPayment><paymentDate>2026-02-06</paymentDate><totalPayment>2886.82</totalPayment><interestPayment>442.38</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>19555.56</remainingDebt></payment><payment><numberPayment>11</numberPayment><paymentDate>2026-03-06</paymentDate><totalPayment>2668.28</totalPayment><interestPayment>223.84</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>17111.11</remainingDebt></payment><payment><numberPayment>12</numberPayment><paymentDate>2026-04-06</paymentDate><totalPayment>2577.06</totalPayment><interestPayment>132.61</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>14666.67</remainingDebt></payment><payment><numberPayment>13</numberPayment><paymentDate>2026-05-06</paymentDate><totalPayment>2531.05</totalPayment><interestPayment>86.60</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>12222.22</remainingDebt></payment><payment><numberPayment>14</numberPayment><paymentDate>2026-06-06</paymentDate><totalPayment>2502.36</totalPayment><interestPayment>57.92</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>9777.78</remainingDebt></payment><payment><numberPayment>15</numberPayment><paymentDate>2026-07-06</paymentDate><totalPayment>2483.35</totalPayment><interestPayment>38.90</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>7333.33</remainingDebt></payment><payment><numberPayment>16</numberPayment><paymentDate>2026-08-06</paymentDate><totalPayment>2469.47</totalPayment><interestPayment>25.03</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>4888.89</remainingDebt></payment><payment><numberPayment>17</numberPayment><paymentDate>2026-09-06</paymentDate><totalPayment>2459.05</totalPayment><interestPayment>14.61</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>2444.44</remainingDebt></payment><payment><numberPayment>18</numberPayment><paymentDate>2026-10-06</paymentDate><totalPayment>2450.96</totalPayment><interestPayment>6.52</interestPayment><debtPayment>2444.44</debtPayment><remainingDebt>0.00</remainingDebt></payment></payments></PaymentSchedule>";
    }

    public static String getTestPaymentScheduleXML_var2() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><PaymentSchedule><payments><payment><numberPayment>1</numberPayment><paymentDate>2025-05-06</paymentDate><totalPayment>2230.95</totalPayment><interestPayment>147.62</interestPayment><debtPayment>2083.33</debtPayment><remainingDebt>22916.67</remainingDebt></payment><payment><numberPayment>2</numberPayment><paymentDate>2025-06-06</paymentDate><totalPayment>2191.93</totalPayment><interestPayment>108.60</interestPayment><debtPayment>2083.33</debtPayment><remainingDebt>20833.33</remainingDebt></payment><payment><numberPayment>3</numberPayment><paymentDate>2025-07-06</paymentDate><totalPayment>2166.22</totalPayment><interestPayment>82.89</interestPayment><debtPayment>2083.33</debtPayment><remainingDebt>18750.00</remainingDebt></payment><payment><numberPayment>4</numberPayment><paymentDate>2025-08-06</paymentDate><totalPayment>2147.32</totalPayment><interestPayment>63.99</interestPayment><debtPayment>2083.33</debtPayment><remainingDebt>16666.67</remainingDebt></payment><payment><numberPayment>5</numberPayment><paymentDate>2025-09-06</paymentDate><totalPayment>2133.13</totalPayment><interestPayment>49.80</interestPayment><debtPayment>2083.33</debtPayment><remainingDebt>14583.33</remainingDebt></payment><payment><numberPayment>6</numberPayment><paymentDate>2025-10-06</paymentDate><totalPayment>2122.22</totalPayment><interestPayment>38.89</interestPayment><debtPayment>2083.33</debtPayment><remainingDebt>12500.00</remainingDebt></payment><payment><numberPayment>7</numberPayment><paymentDate>2025-11-06</paymentDate><totalPayment>2113.33</totalPayment><interestPayment>30.00</interestPayment><debtPayment>2083.33</debtPayment><remainingDebt>10416.67</remainingDebt></payment><payment><numberPayment>8</numberPayment><paymentDate>2025-12-06</paymentDate><totalPayment>2106.13</totalPayment><interestPayment>22.79</interestPayment><debtPayment>2083.33</debtPayment><remainingDebt>8333.33</remainingDebt></payment><payment><numberPayment>9</numberPayment><paymentDate>2026-01-06</paymentDate><totalPayment>3116.67</totalPayment><interestPayment>1033.33</interestPayment><debtPayment>2083.33</debtPayment><remainingDebt>6250.00</remainingDebt></payment><payment><numberPayment>10</numberPayment><paymentDate>2026-02-06</paymentDate><totalPayment>2209.01</totalPayment><interestPayment>125.68</interestPayment><debtPayment>2083.33</debtPayment><remainingDebt>4166.67</remainingDebt></payment><payment><numberPayment>11</numberPayment><paymentDate>2026-03-06</paymentDate><totalPayment>2131.03</totalPayment><interestPayment>47.69</interestPayment><debtPayment>2083.33</debtPayment><remainingDebt>2083.33</remainingDebt></payment><payment><numberPayment>12</numberPayment><paymentDate>2026-04-06</paymentDate><totalPayment>2099.48</totalPayment><interestPayment>16.15</interestPayment><debtPayment>2083.33</debtPayment><remainingDebt>0.00</remainingDebt></payment></payments></PaymentSchedule>";
    }
}
