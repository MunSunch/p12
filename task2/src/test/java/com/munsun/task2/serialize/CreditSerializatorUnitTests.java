package com.munsun.task2.serialize;

import com.munsun.task2.TestUtils;
import com.munsun.task2.model.Credit;
import com.munsun.task2.serialize.impl.CreditSerializator;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CreditSerializatorUnitTests {
    private final AbstractSerializer<Credit> creditSerializator = new CreditSerializator();

    @DisplayName("Test serialize Credit with Annuiety Type to Bytes")
    @Test
    public void givenCreditWithAnnuietyType_whenSerialize_thenCreditBytes() throws JAXBException, DatatypeConfigurationException {
        Credit credit = TestUtils.getTestCreditWithAnnuietyType_var1();
        String expectedString = TestUtils.getTestCreditWithAnnuietyType_toXML_var1();

        try(InputStream inputStream = creditSerializator.serialize(credit)) {
            String actualString = new String(inputStream.readAllBytes());
            assertThat(actualString)
                    .isNotBlank();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Test deserialize Bytes to Credit with Annuiety Type")
    @Test
    public void givenInputStream_whenDeserialize_thenCreditWithAnnuietyType() throws JAXBException, DatatypeConfigurationException {
        String testString = TestUtils.getTestCreditWithAnnuietyType_toXML_var1();
        Credit expectedCredit = TestUtils.getTestCreditWithAnnuietyType_var1();

        Credit actualCredit = creditSerializator.deserialize(new BufferedInputStream(new ByteArrayInputStream(testString.getBytes())));

        assertThat(actualCredit)
                .isNotNull();
    }

    @DisplayName("Test serialize credit with different Type to Bytes")
    @Test
    public void givenCreditWithDifferentType_whenSerialize_thenCreditBytes() throws JAXBException, DatatypeConfigurationException {
        Credit credit = TestUtils.getTestCreditWithDifferentType_var1();
        String expectedString = TestUtils.getTestCreditWithDifferentType_toXML_var1();

        try(InputStream inputStream = creditSerializator.serialize(credit)) {
            String actualString = new String(inputStream.readAllBytes());
            assertThat(actualString)
                    .isNotNull();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Test deserialize Bytes to credit with different Type")
    @Test
    public void givenInputStream_whenDeserialize_thenCreditWithDifferentType() throws JAXBException, DatatypeConfigurationException {
        String testString = TestUtils.getTestCreditWithDifferentType_toXML_var1();
        Credit expectedCredit = TestUtils.getTestCreditWithDifferentType_var1();

        Credit actualCredit = creditSerializator.deserialize(new BufferedInputStream(new ByteArrayInputStream(testString.getBytes())));

        assertThat(actualCredit)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedCredit);
    }

    @DisplayName("Test deserialize Bytes to credit with not valid data")
    @ParameterizedTest
    @MethodSource("generateInvalidCreditXMLSource")
    public void givenInputStream_whenDeserialize_thenCreditWithNotValidType(String testString) throws JAXBException, DatatypeConfigurationException {
        assertThatThrownBy(() ->
            creditSerializator.deserialize(new BufferedInputStream(new ByteArrayInputStream(testString.getBytes())))
        ).isInstanceOf(JAXBException.class);
    }

    public static Stream<Arguments> generateInvalidCreditXMLSource() {
        return Stream.of(
                Arguments.of(TestUtils.getInvalidTestCreditXML_notCreditTypeAttribute()),
                Arguments.of(TestUtils.getInvalidTestCreditXML_notCreditStartDate()),
                Arguments.of(TestUtils.getInvalidTestCreditXML_notCreditAmount()),
                Arguments.of(TestUtils.getInvalidTestCreditXML_notCreditTerm()),
                Arguments.of(TestUtils.getInvalidTestCreditXML_notCreditRate())
        );
    }
}
