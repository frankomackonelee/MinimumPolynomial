package mathsUtils;

public class Fraction {
	//class variables
	
	//instance variables
	private long denominator;
	private long numerator;

	//Constructor
	public Fraction(long numerator, long denominator){
		long hcf = Simplification.highestCommonFactor(numerator, denominator);
		this.numerator = numerator/hcf;
		this.denominator = denominator/hcf;
	}
	
	public Fraction(long numerator){
		this(numerator, 1);
	}
	
	//instance methods
	public long getNumerator(){
		return this.numerator;
	}
	
	public long getDenominator(){
		return this.denominator;
	}
	
	public double getDecimal(){
		return (double)numerator/(double)denominator;
	}
	
	public String getString(){
		return this.numerator + "/" + this.denominator;
	}
	
	public void add(Fraction f1) throws LongOverflowException{
		applyOperationToInstance(f1, "add");
	}
	
	public void multiply(Fraction f1) throws LongOverflowException {
		applyOperationToInstance(f1, "multiply");
	}
	
	public void divide(Fraction by) throws LongOverflowException {
		applyOperationToInstance(by, "divide");
	}
	
	public void subtract(Fraction what) throws LongOverflowException{
		applyOperationToInstance(what, "subtract");
	}
	
	private void applyOperationToInstance(Fraction f1, String operation) throws LongOverflowException{
		Fraction f2 = new Fraction(this.getNumerator(), this.getDenominator());
		switch (operation) {
		case "add":
			f2 = Fraction.add(f1, f2);			
			break;
		case "multiply":
			f2 = Fraction.multiply(f1, f2);			
			break;
		case "subtract":
			f2 = Fraction.subtract(f2, f1);	
			break;
		case "divide":
			f2 = Fraction.divide(f2, f1);			
			break;
		default:
			break;
		}

		this.numerator = f2.getNumerator();
		this.denominator = f2.getDenominator();		
	}
	
	//static methods
	public static Fraction add(Fraction f1, Fraction f2) throws LongOverflowException{
		long commonDenominator = Simplification.lowestCommonMultiple(f1.getDenominator(), f2.getDenominator());
		long f1Multiplier = commonDenominator/f1.getDenominator();
		long f2Mutiplier = commonDenominator/f2.getDenominator();
		long answerNumerator = CheckedOperations.add(
				CheckedOperations.multiply(f1.getNumerator(), f1Multiplier), 
				CheckedOperations.multiply(f2.getNumerator(),f2Mutiplier));
		return new Fraction(answerNumerator, commonDenominator);
	}
	
	public static Fraction subtract(Fraction from, Fraction what) throws LongOverflowException{
		Fraction minusOne = new Fraction(-1);
		what = multiply(minusOne, what);
		return add(from, what);
	}
	
	public static Fraction multiply(Fraction f1, Fraction f2) throws LongOverflowException{
		long d1, d2, n1, n2;
		long hcf1 = Simplification.highestCommonFactor(f1.numerator,f2.denominator);
		if (hcf1 == 0) {
			System.out.println(f1.getNumerator() + "/" + f1.getDenominator() + " x " + f2.getNumerator() + "/" + f2.getDenominator());
		}
		n1 = f1.getNumerator()/hcf1;
		d2 = f2.getDenominator()/hcf1;
		long hcf2 = Simplification.highestCommonFactor(f1.denominator,f2.numerator);
		n2 = f2.getNumerator()/hcf2;
		d1 = f1.getDenominator()/hcf2;
		return new Fraction(
				CheckedOperations.multiply(n1,n2) , 
				CheckedOperations.multiply(d1,d2));
	}
	
	public static Fraction divide(Fraction what, Fraction by) throws LongOverflowException{
		Fraction flipped = new Fraction(by.denominator, by.numerator);
		return Fraction.multiply(what, flipped);
	}
	
	public static long commonDenominator(Fraction[] fractions) throws LongOverflowException{
		//TODO: Make this return a fraction
		long currentLCM = 1L;
		long checkLCM;
		for (int i = 1; i < fractions.length; i++) {
			checkLCM = Simplification.lowestCommonMultiple(fractions[i].getDenominator(),fractions[i-1].getDenominator());
			currentLCM = Math.max(checkLCM, currentLCM);
		}
		return currentLCM;
	}
	
	public static Fraction commonDenominator(Fraction[][] fractions) throws LongOverflowException{
		//TODO: Make this return a fraction
		int length = fractions.length;
		long[] rowOfInts = new long[length];
		for (int i = 0; i < length; i++) {
			long check = commonDenominator(fractions[i]);
			rowOfInts[i] = check;
		}
		return new Fraction(Fraction.lowestCommonMultiple(rowOfInts));
	}
	
	//TODO: This method really belongs in Simplification, and is tested as if it is,
	//But is only called from Fraction so put it here. Could tidy this up a bit
	public static long lowestCommonMultiple(long[] values) throws LongOverflowException{
		if (values.length<1) {
			return -1;
		}else if(values.length==1){
			return values[0];
		}
		long lcm = Simplification.lowestCommonMultiple(values[0], values[1]);
		for (int i = 2; i < values.length; i++) {
			lcm = Simplification.lowestCommonMultiple(lcm, values[i]);
		}
		return lcm;	
	}
}
