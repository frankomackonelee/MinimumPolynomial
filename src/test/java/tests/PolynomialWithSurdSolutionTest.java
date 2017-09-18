package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import polynomialCalculation.PolynomialWithSurdSolution;

public class PolynomialWithSurdSolutionTest {
	//Todo: need to distinguish between expecting a failure and expecting an exception
	
	@Test
	public void printInfo(){
		System.out.println("-----------------------------------------------");
		System.out.println("Now running PolynomialWithSurdSolutionTest.java");
	}

	@Test
	public void test() {
		ArrayList<VariableData> listOfVariables = new ArrayList<VariableData>();

		VariableData p;
		//add variables here:
		//Quadratics
		p = new VariableData(3,2, new int[]{0,1});
		listOfVariables.add(p);
		
		p = new VariableData(5,2, new int[]{2,-1});
		listOfVariables.add(p);
		
		//Cubics
		p = new VariableData(2, 3, new int[]{1,1,0});
		listOfVariables.add(p);
		
		p = new VariableData(2, 3, new int[]{1,3,-1});
		listOfVariables.add(p);

		p = new VariableData(2, 3, new int[]{1,0,1});
		listOfVariables.add(p);

		p = new VariableData(2, 3, new int[]{5,7,-6});
		listOfVariables.add(p);
		
		//Quintics
		p = new VariableData(2, 5, new int[]{1,1,1,1,1});
		listOfVariables.add(p);
		
		p = new VariableData(2, 5, new int[]{1,-1,1,-1,1});
		listOfVariables.add(p);

		p = new VariableData(2, 5, new int[]{2,-2,2,-2,2});
		listOfVariables.add(p);
		
		//The following are too big to handle the inverse using ints in the matrix
		p = new VariableData(2, 5, new int[]{1,1,1,0,2});
		listOfVariables.add(p);
		
		p = new VariableData(2, 5, new int[]{1,1,1,1,2});
		listOfVariables.add(p);		

		p = new VariableData(2, 5, new int[]{11,11,11,11,11});
		listOfVariables.add(p);	

		p = new VariableData(7, 5, new int[]{11,11,11,11,11});
		listOfVariables.add(p);	

		//This one is too big for a long
		p = new VariableData(7, 5, new int[]{11,11,11,11,21}, true);
		listOfVariables.add(p);	
		
		PolynomialWithSurdSolution currentPolynomial;
		
		for (Iterator iterator = listOfVariables.iterator(); iterator.hasNext();) {
			VariableData variable = (VariableData) iterator.next();
			
			try {
				currentPolynomial = new PolynomialWithSurdSolution(variable.base, variable.nthPower, variable.nthPowerMultiples);
				
				System.out.println("x = " + currentPolynomial.stringSurd() + " = " + currentPolynomial.getValuePowerOfX(0));
				System.out.println(currentPolynomial.stringPolynomial() + " = " + currentPolynomial.evaluatePolynomialAtSolution()); 
				assertEquals(0, currentPolynomial.evaluatePolynomialAtSolution(), 0.0001);
				if (variable.willFail == true) {
					System.out.println("An error should have been thrown");
					fail("An error should have been thrown");
				}
				System.out.println("-----------");			
				
			} catch (Exception e) {
				if (variable.willFail == true) {
					System.out.println(e.getMessage());
					System.out.println("Data was anticipated to cause a problem");
					assertFalse(false);
				}else{
					System.out.println(e.getMessage());
					fail("Error should not have been thrown");
				}
				// TODO: handle exception
			}

		}			

	}
}
