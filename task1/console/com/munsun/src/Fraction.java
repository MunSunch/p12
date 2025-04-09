package com.munsun;

public class Fraction {
	private int integerPart;
	private int numerator;
	private int denominator;
	
	private static final String DEFAULT_PATTERN_MATCHER = "^\\d \\d/\\d$";

	public Fraction(int integerPart, int numerator, int denominator) {
		setDenominator(denominator);
		setNumerator(numerator);
		setIntegerPart(integerPart);
	}

	public void setIntegerPart(int integerPart) {
		this.integerPart = integerPart;
	}
	public void setNumerator(int numerator) {
		if(numerator == 0) {
			throw new IllegalArgumentException("Числитель не может быть равен нулю");
		}
		this.numerator = numerator;
	}
	public void setDenominator(int denominator) {
		if(denominator == 0) {
			throw new IllegalArgumentException("Знаменатель не может быть равен нулю");
		}
		this.denominator = denominator;
	}
	
	public Fraction add(Fraction anotherFraction) {
		var firstOperand = this.toIncorrectFraction();
		var secondOperand = anotherFraction.toIncorrectFraction();
		int commonDenominator = Math.nok(firstOperand.getDenominator(), secondOperand.getDenominator());
		return new Fraction(
			0,
			commonDenominator/firstOperand.getDenominator()*firstOperand.getNumerator() 
				+ commonDenominator/secondOperand.getDenominator()*secondOperand.getNumerator() ,
			commonDenominator
		);
	}

	public Fraction minus(Fraction anotherFraction) {
		var firstOperand = this.toIncorrectFraction();
		var secondOperand = anotherFraction.toIncorrectFraction();
		int commonDenominator = Math.nok(firstOperand.getDenominator(), secondOperand.getDenominator());
		return new Fraction(
			0,
			commonDenominator/firstOperand.getDenominator()*firstOperand.getNumerator() 
				- commonDenominator/secondOperand.getDenominator()*secondOperand.getNumerator() ,
			commonDenominator
		);
	}

	public Fraction mult(Fraction anotherFraction) {
		var firstOperand = this.toIncorrectFraction();
		var secondOperand = anotherFraction.toIncorrectFraction();
		return new Fraction(
			0,
			firstOperand.getNumerator()*secondOperand.getNumerator(),
			firstOperand.getDenominator()*secondOperand.getDenominator()
		);
	}

	public Fraction div(Fraction anotherFraction) {
		var firstOperand = this.toIncorrectFraction();
		var secondOperand = anotherFraction.toIncorrectFraction();
		return new Fraction(
			0,
			firstOperand.getNumerator()*secondOperand.getDenominator(),
			firstOperand.getDenominator()*secondOperand.getNumerator()
		);
	}

	@Override
	public String toString() {
		return String.format("%d %d/%d", this.integerPart, this.numerator, this.denominator);
	}
	
	public Fraction toIncorrectFraction() {
		if(this.integerPart == 0) {
			return this;
		}	
		return new Fraction(
			0,
			this.integerPart * this.denominator + this.numerator,
			this.denominator
		);
	}
	
	public Fraction toMixedFraction() {
		try {
			return new Fraction(
					this.getNumerator() / this.getDenominator(),
					this.getNumerator() % this.getDenominator(),
					this.getDenominator()
			);
		} catch (IllegalArgumentException e) {
			return this;
		}
	}

	public Fraction shortFraction() {
		var fraction = this.toIncorrectFraction();
		int nod = Math.nod(fraction.getNumerator(), fraction.getDenominator());
		int newNumerator = fraction.getNumerator()/nod;
		int newDenominator = fraction.getDenominator()/nod;
		if(newDenominator == 1) {
			return new Fraction(0, newNumerator, newDenominator);
		}
		return new Fraction(0, newNumerator, newDenominator);
	}
	
	public static Fraction toFraction(String str) {
		if(!str.matches(DEFAULT_PATTERN_MATCHER)) {
			throw new IllegalArgumentException("Строка должна соответствовать шаблону: ^\\d \\d/\\d$");
		}
		String[] parts = str.split(" ");
		int integerPart = Integer.parseInt(parts[0]);
		String[] fraction = parts[1].split("/");
		int numerator = Integer.parseInt(fraction[0]);
		int denominator = Integer.parseInt(fraction[1]);
		return new Fraction(integerPart, numerator, denominator);
	}
	
	public static Fraction toFraction(int integerNumber) {
		return new Fraction(0, integerNumber, 1);
	}
	
	public static Fraction toFraction(double number) {
		var parts = String.valueOf(number).split("\\.");
		int denominator = Math.pow(10, parts[1].length());
		int numerator = Integer.parseInt(parts[1]);
		int integerPart = Integer.parseInt(parts[0]);
		if(numerator == 0) {
			return new Fraction(0, integerPart, 1);
		}
		return new Fraction(integerPart, numerator, denominator);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Fraction other = (Fraction) obj;
		return integerPart == other.integerPart &&
				numerator == other.numerator &&
				denominator == other.denominator;
	}

	public int getIntegerPart() {return this.integerPart;}
	public int getNumerator() {return this.numerator;}
	public int getDenominator() {return this.denominator;}
}