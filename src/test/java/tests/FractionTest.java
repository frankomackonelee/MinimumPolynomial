package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import mathsUtils.Fraction;

public class FractionTest {
	@Test
	public void printInfo(){
		System.out.println("-----------------------------------------------");
		System.out.println("Now running FractionTest.java");
	}
	
	@Test 
	public void test_Fraction(){
		Fraction f1 = new Fraction(91,39);
		assertEquals(7, f1.getNumerator());
		assertEquals(3, f1.getDenominator());
		assertEquals(2.33333, f1.getDecimal(),0.001);
		
		f1 = new Fraction(11);
		assertEquals(11, f1.getNumerator());
		assertEquals(1, f1.getDenominator());
		assertEquals(11, f1.getDecimal(),0.001);
	}
	
	@Test
	public void test_getDecimal() {
		Fraction f1 = new Fraction(20,100);
		assertEquals(0.2, f1.getDecimal(),0.001);
		f1 = new Fraction(20,140);
		assertEquals(0.1428, f1.getDecimal(),0.001);
	}
	
	@Test
	public void test_lowestCommonMultiple1(){
		try {
			Fraction f1 = new Fraction(6,11);
			Fraction f2 = new Fraction(3,4);
			Fraction f3 = new Fraction(5,2);
			
			Fraction[] arrFraction = {f1, f2, f3};
			
			long LCM = Fraction.commonDenominator(arrFraction);
			
			assertEquals(44, LCM);				
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error was thrown when it should not have been");
			System.out.println(e.getMessage());
		}

	}	
	
	@Test
	public void test_lowestCommonMultiple2(){
		try {
			Fraction f1 = new Fraction(5,12);
			Fraction f2 = new Fraction(3,4);
			
			Fraction[] arrFraction = {f1, f2};
			
			long LCM = Fraction.commonDenominator(arrFraction);
			
			assertEquals(12, LCM);				
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error was thrown when it should not have been");
			System.out.println(e.getMessage());
		}

	}	
}
