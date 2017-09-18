package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import polynomialCalculation.Surd;

public class SurdTest {
	@Test
	public void printInfo(){
		System.out.println("-----------------------------------------------");
		System.out.println("Now running SurdTest.java");
	}

	@Test
	public void test_Surd() {
		Surd x = new Surd(2, 5, new int[]{3, 1, -1, 2, -2});
		assertEquals(2.3784213,x.getValue(),0.0000001);
		
		try {
			Surd y = new Surd(2, 5, new int[]{3, 1, -1, 2, -2,  2});
		} catch (IndexOutOfBoundsException e) {
			
		} 
		
		try {
			Surd z = new Surd(2, 5, new int[]{3, 1, -1, 2,});

		} catch (IndexOutOfBoundsException e) {

		} 
	}
	
	@Test
	public void test_getValue(){
		int testCount = 6;
		Surd[] mySurds = new Surd[testCount];
		double[] myValues = new double[testCount];
		
		mySurds[0] = new Surd(3,5,new int[]{1, -1, 1, -1, 1});
		myValues[0] = 1.78116;
		
		mySurds[1] = new Surd(2,5,new int[]{2, 1, 1, 1, 5});
		myValues[1] = 14.68943;
		
		mySurds[2] = new Surd(2,4,new int[]{2, 1, 1, 1});
		myValues[2] = 6.28521;

		mySurds[3] = new Surd(5,3,new int[]{-2, -1, -1});
		myValues[3] = -6.63399;

		mySurds[4] = new Surd(5,6,new int[]{0, 1, 0, 1, 2, -3});
		myValues[4] = -2.0791;
		
		mySurds[5] = new Surd(5,3,new int[]{2, -1, 1});
		myValues[5] = 3.21404;

		for (int i = 0; i < myValues.length; i++) {
			assertEquals(myValues[i], mySurds[i].getValue(),0.00001);
		}
	}

}
