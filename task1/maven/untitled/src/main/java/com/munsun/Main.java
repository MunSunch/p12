package com.munsun;

public class Main {
    public static void main(String... args) {
        var fraction = new Fraction(1,21,3);
        System.out.println("дробь сокращенная: " + fraction.shortFraction().toMixedFraction());

        // Операции
        var firstOperand = new Fraction(1, 7, 3);
        var secondOperand = new Fraction(2, 5, 3);
        System.out.println(String.format("%s + %s = %s = %s", firstOperand, secondOperand, firstOperand.add(secondOperand), firstOperand.add(secondOperand).toMixedFraction()));
        System.out.println(String.format("%s - %s = %s = %s", firstOperand, secondOperand, firstOperand.minus(secondOperand), firstOperand.minus(secondOperand).toMixedFraction()));
        System.out.println(String.format("%s * %s = %s = %s", firstOperand, secondOperand, firstOperand.mult(secondOperand), firstOperand.mult(secondOperand).toMixedFraction()));
        System.out.println(String.format("%s / %s = %s = %s", firstOperand, secondOperand, firstOperand.div(secondOperand), firstOperand.div(secondOperand).toMixedFraction()));
        System.out.println();

        // Преобразования
        System.out.println("из строки: " + Fraction.toFraction("1 2/9"));
        System.out.println("из целого числа: " + Fraction.toFraction(12));
        System.out.println("из вещественного числа: " + Fraction.toFraction(12.1234D));
    }
}