package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mathsUtils.Fraction;
import mathsUtils.LongOverflowException;

public class FractionTestFromFile {
	
	@Test
	public void test(){
		//Add test
		test_from_file("C:/Users/fmalo/workspace/MinimumPolynomial/TestCases/TestValues/FractionTest_Add.txt", "add");	
		test_from_file("C:/Users/fmalo/workspace/MinimumPolynomial/TestCases/TestValues/FractionTest_Subtract.txt", "subtract");
		test_from_file("C:/Users/fmalo/workspace/MinimumPolynomial/TestCases/TestValues/FractionTest_Multiply.txt", "multiply");
		test_from_file("C:/Users/fmalo/workspace/MinimumPolynomial/TestCases/TestValues/FractionTest_Divide.txt", "divide");		
	}
	
	public void test_from_file(String location, String operation) {
		System.out.println("----Testing " + operation + "--------");
		checkFile(location);
		
		List<String> lines = new ArrayList<String>();
		try  {
			lines = Files.readAllLines(Paths.get(location));
		} catch (Exception e) {
			fail("Problem - need to work out what it is");
		}
		
		//First line will contain a brief description so step over it.
		lines.remove(0);
		//String[] columnHeadings = lines.get(0).split("\t");
		lines.remove(0);
		
		String[] inputs;
		for (String line : lines){
			inputs = line.split("\t");
			runTest(	Long.parseLong(inputs[0]),
						Long.parseLong(inputs[1]),
						Long.parseLong(inputs[2]),
						Long.parseLong(inputs[3]),
						Long.parseLong(inputs[4]),
						operation,
						inputs[5].toLowerCase().trim().equals("true"),
						Long.parseLong(inputs[6]),
						Long.parseLong(inputs[7]),
						inputs[8].toLowerCase().trim().equals("true")
					);
		}
	}
	
	private void checkFile(String location) {
		if (!new File(location).exists()){
		   try {
				throw new FileNotFoundException("The following file does not exist: '" + location + "'");
			} catch (FileNotFoundException e) {
				fail(e.getMessage());
			}
		}
	}
	
	private void runTest(long caseNumber, long numerator1, long denominator1, long numerator2, long denominator2, String operation, Boolean staticIndicator, long numerator3, long denominator3, boolean willThrowError){
		Fraction f1 = new Fraction(numerator1,denominator1);
		Fraction f2 = new Fraction(numerator2,denominator2);
		Fraction answer = null;
		try {
			switch (operation) {
			case "add":
					answer = Fraction.add(f1, f2);
					f1.add(f2);		
				break;

			case "multiply":
				answer = Fraction.multiply(f1, f2);
				f1.multiply(f2);					
				break;

			case "subtract":
				answer = Fraction.subtract(f1, f2);
				f1.subtract(f2);				
				break;

			case "divide":
				answer = Fraction.divide(f1, f2);
				f1.divide(f2);					
				break;

			default:
				break;
			}
			
			if(staticIndicator){
				assertEquals(numerator3, answer.getNumerator());
				assertEquals(denominator3, answer.getDenominator());				
			}else{
				assertEquals(numerator3, f1.getNumerator());
				assertEquals(denominator3, f1.getDenominator());					
			}

			
		} catch (LongOverflowException e) {
			if(willThrowError){
				System.out.println(e.getMessage());
				System.out.println("An exception was expected");
				assertTrue(true);
			}else{
				System.out.println(e.getMessage());
				fail("An exception was thrown when it should not have been in test " + caseNumber);
			}
		} catch (AssertionError e) {
			if(willThrowError){
				fail("An exception should have been thrown but was not");
			}else{
				throw new AssertionError(e.getMessage());				
			}
		} catch (Exception e){
			fail("An unexpected error was thrown.  Investigate case " + caseNumber);			
		}

	}
}
