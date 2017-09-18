package polynomialCalculation;

import mathsUtils.Fraction;
import mathsUtils.Matrix;

public class PolynomialWithSurdSolution extends PowersOfSurd {
	/*
		for a third power surd:
		c:
		x: 		(		)
		x^2:	(		)
		x^3:	(		)
		so:
		(	a	,b	,c) * 	("Surd"	) = (k , 0,  0)...k00
							(		)
							(		)
		
		ax + bx^2 + cx^3 - k = 0
		(kMatrix)		(inverseOfMultiples) = fractionalCoefficients
		
		(	1,	0,	0)	(	1	,-1	,1/3	) = (	a	,b	,c)
						(	0	,1	,-1/3	)
						(	-1	,0	,1/3	)
						
		coefficientsOfPowersOfX = fractionalCoefficients * (commonDenominator)
		
		polynomialPowers[0] + x*polynomialPowers[1] + x^2*polynomialPowers[2] + x^3*polynomialPowers[3] = 0
	*/
	
	//instance 
	final private Fraction[] polynomialPowers;
	
	//constructor
	public PolynomialWithSurdSolution(int base, int nthPower, int[] nthPowerMultiples) throws Exception {
		// TODO Auto-generated constructor stub
		super( base,  nthPower, nthPowerMultiples);
		Matrix xAndPowersOfX = new Matrix(super.powersOfX);
		int[][] k = new int[1][nthPower];
		k[0][0] = 1;
 		Matrix kMatrix = new Matrix(k);
		Matrix inverseOfMultiples = Matrix.getInverse(xAndPowersOfX);
		Matrix fractionalCoefficients = Matrix.multiply(kMatrix, inverseOfMultiples);
		Matrix coefficientsOfPowersOfX = Matrix.multiplyByCommonDenominator(fractionalCoefficients);
		Matrix k00 = Matrix.multiply(coefficientsOfPowersOfX, xAndPowersOfX);
		
		Fraction minusOne = new Fraction(-1);
		polynomialPowers = new Fraction[nthPower + 1];
		minusOne.multiply(k00.getValue(0, 0));
		polynomialPowers[0] = minusOne;
		for (int i = 0; i < nthPower; i++) {
			polynomialPowers[i+1] = coefficientsOfPowersOfX.getValue(0, i);
		}
	}
	
	//instance methods
	
	public String stringPolynomial(){
		String answer = "";

		for (int i = this.nthPower; i >= 0; i--) {
			answer += i < this.nthPower ? " + " : "";
			if(i == 0 ){
				answer += this.getPowerOfX(i);
			}else if(i == 1){
				answer += this.getPowerOfX(i) + "x";
			}else{
				answer += this.getPowerOfX(i) + "x^" +  i + " ";
			}
		}
		
		return answer;
		
	}
	
	public double evaluatePolynomialAtSolution(){
		//TODO: use BigDecimal to do this
		return evaluatePolynomial(getValuePowerOfX(0));
	}
	
	private long getPowerOfX(int exponent){
		return polynomialPowers[exponent].getNumerator();
	}
	
	private double evaluatePolynomial(double x){
		double answer = 0;
		for (int i = 0; i < this.nthPower + 1; i++) {
			answer += this.getPowerOfX(i)*Math.pow(x, i);
		}		
		return answer;
	}

}
