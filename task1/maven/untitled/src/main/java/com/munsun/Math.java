package com.munsun;

public class Math {
	public static int nod(int a, int b) {
        	while (b != 0) {
            		int temp = b;
            		b = a % b;
            		a = temp;
        	}
        	return a;
   	}

	public static int nok(int a, int b) {
		return (a / nod(a, b)) * b;
	}

	public static int pow(int number, int degree) {
		int result = 1;
		while (degree != 0) {
			result *= number;
			degree--;
		}
		return result;
	}
}