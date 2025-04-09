package com.munsun;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MathUnitTests {

    @DisplayName("Тест корректного подсчета наибольшего общего делителя")
    @ParameterizedTest
    @MethodSource("provideNumbersForNOD")
    public void givenNumbers_whenNOD_thenReturnCorrectNOD(int firstNumber, int secondNumber, int expectedNOD) {
        var actualNOD = Math.nod(firstNumber, secondNumber);

        assertThat(actualNOD)
                .isPositive()
                .isEqualTo(expectedNOD);
    }

    public static Stream<Arguments> provideNumbersForNOD() {
        return Stream.of(
                Arguments.of(12, 8, 4),
                Arguments.of(12, 9, 3),
                Arguments.of(25, 15, 5),
                Arguments.of(25, 16, 1)
        );
    }

    @DisplayName("Тест корректного подсчета наибольшего общего кратного")
    @ParameterizedTest
    @MethodSource("provideNumbersForNOK")
    public void givenNumbers_whenNOK_thenReturnCorrectNOK(int firstNumber, int secondNumber, int expectedNOK) {
        var actualNOK = Math.nok(firstNumber, secondNumber);

        assertThat(actualNOK)
                .isEqualTo(expectedNOK);
    }

    public static Stream<Arguments> provideNumbersForNOK() {
        return Stream.of(
                Arguments.of(12, 8, 24),
                Arguments.of(12, 9, 36),
                Arguments.of(25, 15, 75),
                Arguments.of(25, 16, 400),
                Arguments.of(0, 1, 0)
        );
    }

    @DisplayName("Тест корректного подсчета возведения числа в степень")
    @ParameterizedTest
    @MethodSource("provideNumbersForPow")
    public void givenNumbers_whenPow_thenReturnCorrectPow(int firstNumber, int secondNumber, int expectedPow) {
        var actualPow = Math.pow(firstNumber, secondNumber);

        assertThat(actualPow)
                .isEqualTo(expectedPow);
    }

    public static Stream<Arguments> provideNumbersForPow() {
        return Stream.of(
                Arguments.of(2, 3, 8),
                Arguments.of(2, 4, 16),
                Arguments.of(3, 2, 9),
                Arguments.of(3, 3, 27),
                Arguments.of(3, 4, 81),
                Arguments.of(10, 0, 1)
        );
    }
}
