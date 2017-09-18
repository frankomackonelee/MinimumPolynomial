package polynomialCalculation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputFromCommandLine {
	static Integer base;
	static Integer nthPower;
	static int nthPowerMultiples[];
	
	public static void main(String[] args) {

		System.out.println("This process will find you a polynomial with a solutions being the surd you specify");
		System.out.println("Surd must be of the form of x = k + l*b^(1/n) + ... + m*b^((n-1)/n)");
		System.out.println("Where b, n, k - m all represent integers");

		try (Scanner scan = new Scanner(System.in)) {
			
			promptInputConfirm(	scan, "base",
								"Input the base (represented by b above)");
			
			promptInputConfirm(	scan, "denominator",
					"Input a denominator for the fractional powers (represented by n above)");
			
			nthPowerMultiples = new int[nthPower];
			for (int i = 0; i < nthPower; i++) {
				String prompt;
				String confirmation;
				if(i == 0){
					prompt = "choose the integer component of the surd ie k above";
					confirmation = "integer component (k) - ie when numerator in the power is " + i;
				}else if(i == 1){
					prompt = String.format("chose the multiple of %1$d^(%2$d/%3$d) ie l above", base, i, nthPower);
					confirmation = "multiple when numerator in the power is " + i;
				}else{
					prompt = String.format("chose the multiple of %1$d^(%2$d/%3$d)", base, i, nthPower);
					confirmation = "multiple when numerator of the power is " + i;
				}
				promptInputConfirm(scan, confirmation, prompt);
			}

			PolynomialWithSurdSolution myPolynomial = new PolynomialWithSurdSolution(base, nthPower, nthPowerMultiples);
			System.out.println("For x = " + myPolynomial.stringSurd());
			System.out.println(myPolynomial.stringPolynomial() + " = 0");
			
		} catch (InputMismatchException e) {
			System.err.println(e.getMessage());
			
		}	catch(Exception e){
			System.err.println("The following exception resulted in a generic catch.  Need to make it more specific");
			System.err.println(e);
			e.printStackTrace(System.err);
		}	
	}
	
	private static void promptInputConfirm(Scanner scan, String dataType, String prompt) throws InputMismatchException{
		int valueTaken;
		System.out.println(prompt);
		if(scan.hasNextInt()){
			valueTaken = scan.nextInt();				
		}else{
			throw new InputMismatchException("The " + dataType + " must be an integer");
		}
		System.out.format("Using a " + dataType + " of: %d\n", valueTaken);		
		String[] dataTypes = dataType.split(" ");
		int index = 0;
		if(dataTypes.length>1){
			index = Integer.parseInt(dataTypes[dataTypes.length - 1]);			
		}
		switch (dataTypes[0]) {
			case "base":
				base = valueTaken;
				break;
	
			case "denominator":
				nthPower = valueTaken;
				break;
	
			case "multiple":
			case "integer":
				nthPowerMultiples[index] = valueTaken;
				break;
				
			default:
				break;
		}
	}
}
