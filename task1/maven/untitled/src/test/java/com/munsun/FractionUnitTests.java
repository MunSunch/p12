package com.munsun;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FractionUnitTests {

    @DisplayName("Тест на выброс ошибки в случае создания дроби со знаменателем равным 0")
    @Test
    public void whenCreateFractionWithZeroDenominator_thenThrowException() {
        assertThatThrownBy(
                () -> new Fraction(0, 1, 0)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Тест корректной формы значения toString()")
    @Test
    public void givenFraction_whenToString_thenReturnCorrectFormString() {
        String expectedFormString = "5 2/4";
        var fraction = new Fraction(5, 2, 4);

        var actualFormString = fraction.toString();

        assertThat(actualFormString)
                .isNotBlank()
                .isEqualTo(expectedFormString);
    }

    @DisplayName("Тест трансформации смешанной дроби в неправильную дробь")
    @Test
    public void givenFraction_whenToIncorrectFraction_thenReturnIncorrectFraction() {
        var expectedIncorrectFraction = new Fraction(0, 22, 4);
        var fraction = new Fraction(5, 2, 4);

        var actualIncorrectFraction = fraction.toIncorrectFraction();

        assertThat(actualIncorrectFraction)
                .isNotNull()
                .isEqualTo(expectedIncorrectFraction);
    }

    @DisplayName("Тест трансформации неправильной дроби в смешанную дробь")
    @Test
    public void givenFraction_whenToMixedFraction_thenReturnMixedFraction() {
        var expectedMixedFraction = new Fraction(2, 2, 10);
        var fraction = new Fraction(0, 22, 10);

        var actualMixedFraction = fraction.toMixedFraction();

        assertThat(actualMixedFraction)
                .isNotNull()
                .isEqualTo(expectedMixedFraction);
    }

    @DisplayName("Тест корректности вычисления суммы дробей")
    @ParameterizedTest
    @MethodSource("provideFractionsForSum")
    public void givenFractions_whenSum_thenReturnCorrectSum(Fraction firstFraction, Fraction secondFraction, Fraction expectedSum) {
        var actualSum = firstFraction.add(secondFraction);

        assertThat(actualSum)
                .isNotNull()
                .isEqualTo(expectedSum);
    }

    public static Stream<Arguments> provideFractionsForSum() {
        return Stream.of(
                Arguments.of(new Fraction(0, 1, 2),
                             new Fraction(0, 2, 3),
                             new Fraction(0, 7, 6)),
                Arguments.of(new Fraction(0, 1, 2),
                             new Fraction(0, 3, 4),
                             new Fraction(0, 5, 4)),
                Arguments.of(new Fraction(1, 1, 2),
                             new Fraction(2, 1, 4),
                             new Fraction(0, 15, 4)),
                Arguments.of(new Fraction(0, 1, 2),
                             new Fraction(0, 3, 5),
                             new Fraction(0, 11, 10))
        );
    }


    @DisplayName("Тест корректности вычисления разности дробей")
    @ParameterizedTest
    @MethodSource("provideFractionsForMinus")
    public void givenFractions_whenMinus_thenReturnCorrectResult(Fraction firstFraction, Fraction secondFraction, Fraction expectedResult) {
        var actualResult = firstFraction.minus(secondFraction);

        assertThat(actualResult)
                .isNotNull()
                .isEqualTo(expectedResult);
    }

    public static Stream<Arguments> provideFractionsForMinus() {
        return Stream.of(
                Arguments.of(new Fraction(0, 1, 2), // 1/2 - 2/3=3-4/6
                             new Fraction(0, 2, 3),
                             new Fraction(0, -1, 6)),
                Arguments.of(new Fraction(2, 1, 2), // 5/2 - 7/4
                             new Fraction(1, 3, 4),
                             new Fraction(0, 3, 4))
        );
    }

    @DisplayName("Тест корректности вычисления произведения дробей")
    @ParameterizedTest
    @MethodSource("provideFractionsForMultiple")
    public void givenFractions_whenMultiple_thenReturnCorrectResult(Fraction firstFraction, Fraction secondFraction, Fraction expectedResult) {
        var actualResult = firstFraction.mult(secondFraction);

        assertThat(actualResult)
                .isNotNull()
                .isEqualTo(expectedResult);
    }

    public static Stream<Arguments> provideFractionsForMultiple() {
        return Stream.of(
                Arguments.of(new Fraction(0, 1, 2),
                        new Fraction(0, 2, 3),
                        new Fraction(0, 2, 6)),
                Arguments.of(new Fraction(2, 1, 2), // 5/2 * 7/4
                        new Fraction(1, 3, 4),
                        new Fraction(0, 35, 8))
        );
    }

    @DisplayName("Тест корректности вычисления деления дробей")
    @ParameterizedTest
    @MethodSource("provideFractionsForDivide")
    public void givenFractions_whenDivide_thenReturnCorrectResult(Fraction firstFraction, Fraction secondFraction, Fraction expectedResult) {
        var actualResult = firstFraction.div(secondFraction);

        assertThat(actualResult)
                .isNotNull()
                .isEqualTo(expectedResult);
    }

    public static Stream<Arguments> provideFractionsForDivide() {
        return Stream.of(
                Arguments.of(new Fraction(0, 1, 2),
                        new Fraction(0, 2, 3),
                        new Fraction(0, 3, 4)),
                Arguments.of(new Fraction(2, 1, 2), // 5/2 * 4/7
                        new Fraction(1, 3, 4),
                        new Fraction(0, 20, 14))
        );
    }

    @DisplayName("Тест корректности преобразования строки в дробь")
    @Test
    public void givenString_whenToFraction_thenReturnCorrectFraction() {
        var expectedFraction = new Fraction(5, 2, 4);
        var stringFraction = "5 2/4";

        var actualFraction = Fraction.toFraction(stringFraction);

        assertThat(actualFraction)
                .isNotNull()
                .isEqualTo(expectedFraction);
    }

    @DisplayName("Тест выброса ошибки в случае преобразования строки в дробь")
    @Test
    public void givenIncorrectString_whenToFraction_thenThrowException() {
        var stringFraction = "2/4";

        assertThatThrownBy(() -> Fraction.toFraction(stringFraction))
            .isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("Тест корректности преобразования целого числа в дробь")
    @Test
    public void givenIntegerNumber_whenToFraction_thenReturnCorrectFraction() {
        var expectedFraction = new Fraction(0, 55, 1);
        var integerNumber = 55;

        var actualFraction = Fraction.toFraction(integerNumber);

        assertThat(actualFraction)
                .isNotNull()
                .isEqualTo(expectedFraction);
    }

    @DisplayName("Тест корректности преобразования вещественного числа в дробь")
    @Test
    public void givenDoubleNumber_whenToFraction_thenReturnCorrectFraction() {
        var expectedFraction = new Fraction(12, 123, 1000);
        var doubleNumber = 12.123;

        var actualFraction = Fraction.toFraction(doubleNumber);

        assertThat(actualFraction)
                .isNotNull()
                .isEqualTo(expectedFraction);
    }

    @DisplayName("Тест преобразования вещественного числа с нулевой частью числителя в дробь")
    @Test
    public void givenDoubleNumberWithZeroPartNumerator_whenToFraction_thenThrowException() {
        var expectedFraction = new Fraction(0, 12, 1);
        var doubleNumber = 12.0;

        var actualFraction = Fraction.toFraction(doubleNumber);

        assertThat(actualFraction)
                .isNotNull()
                .isEqualTo(expectedFraction);
    }
}
