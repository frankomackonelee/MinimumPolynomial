package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import mathsUtils.Fraction;
import mathsUtils.Matrix;

public class MatrixTest {
	@Test
	public void printInfo(){
		System.out.println("-----------------------------------------------");
		System.out.println("Now running MatrixTest.java");
	}

	@Test
	public void test_Matrix() {
		Fraction f11 = new Fraction(1);
		Fraction f12 = new Fraction(-3);
		Fraction f21 = new Fraction(4);
		Fraction f22 = new Fraction(2);
		Fraction[][] outputArray = new Fraction[][]{{f11, f12},{f21, f22}};
		
		int[] row1 = new int[]{1,-3};
		int[] row2 = new int[]{4,2};
		
		int[][] inputArray = new int[2][2];
		inputArray[0] = row1;
		inputArray[1] = row2;
		
		Matrix myMatrix = new Matrix(inputArray);
		
		checkArrayValues(myMatrix.getValues(), outputArray);
		
	}

	@Test
	public void test_multiply1() {
		//Can you see comments
		Fraction f11 = new Fraction(7);
		Fraction f12 = new Fraction(10);
		Fraction f21 = new Fraction(15);
		Fraction f22 = new Fraction(22);
		Fraction[][] expected = new Fraction[][]{{f11, f12},{f21, f22}};
		
		int[] row1 = new int[]{1,2};
		int[] row2 = new int[]{3,4};
		
		int[][] inputArray = new int[2][2];
		inputArray[0] = row1;
		inputArray[1] = row2;
		
		Matrix myMatrix = new Matrix(inputArray);
		Matrix theProduct;
		try {
			theProduct = Matrix.multiply(myMatrix, myMatrix );
			checkArrayValues( expected, theProduct.getValues());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Test should not have thrown error");
			System.out.println(e.getMessage());
		}	
		
	}
	
	@Test
	public void test_multiply2() {
		Fraction f11 = new Fraction(3);
		Fraction f12 = new Fraction(-2);
		Fraction f21 = new Fraction(7);
		Fraction f22 = new Fraction(-4);
		Fraction[][] expected = new Fraction[][]{{f11, f12},{f21, f22}};
		
		int[] row1 = new int[]{1,2};
		int[] row2 = new int[]{3,4};
		
		int[][] inputArray = new int[2][2];
		inputArray[0] = row1;
		inputArray[1] = row2;

		row1 = new int[]{1,0};
		row2 = new int[]{1,-1};
		
		int[][] inputArraySecond = new int[2][2];
		inputArraySecond[0] = row1;
		inputArraySecond[1] = row2;
		
		Matrix myMatrix1 = new Matrix(inputArray);
		Matrix myMatrix2 = new Matrix(inputArraySecond);
		Matrix theProduct;
		try {
			theProduct = Matrix.multiply(myMatrix1, myMatrix2 );
			checkArrayValues( expected, theProduct.getValues());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Test should not have thrown error");
			System.out.println(e.getMessage());
		}

		
	}	
	
	@Test
	public void test_multiply3() {
		Fraction f11 = new Fraction(4);
		Fraction f12 = new Fraction(-2);
		Fraction f21 = new Fraction(10);
		Fraction f22 = new Fraction(-5);
		
		Fraction[][] expected = new Fraction[][]{{f11, f12},{f21, f22}};
		
		int[] row1 = new int[]{1,2,3};
		int[] row2 = new int[]{4,5,6};
		
		int[][] inputArray = new int[2][3];
		inputArray[0] = row1;
		inputArray[1] = row2;
		
		int[] row3;
		row1 = new int[]{1,0};
		row2 = new int[]{0,-1};
		row3 = new int[]{1,0};
		
		int[][] inputArraySecond = new int[3][2];
		inputArraySecond[0] = row1;
		inputArraySecond[1] = row2;
		inputArraySecond[2] = row3;
		
		Matrix myMatrix1 = new Matrix(inputArray);
		Matrix myMatrix2 = new Matrix(inputArraySecond);
		Matrix theProduct;
		try {
			theProduct = Matrix.multiply(myMatrix1, myMatrix2 );
			checkArrayValues( expected, theProduct.getValues());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Test should not have thrown error");
			System.out.println(e.getMessage());
		}
		
	}		

	@Test
	public void test_multiply4() {
		
		int[] row1 = new int[]{1,2,3,-1};
		int[] row2 = new int[]{4,5,6,-2};
		
		int[][] inputArray = new int[2][3];
		inputArray[0] = row1;
		inputArray[1] = row2;
		
		int[] row3;
		row1 = new int[]{1,0};
		row2 = new int[]{0,-1};
		row3 = new int[]{1,0};
		
		int[][] inputArraySecond = new int[3][2];
		inputArraySecond[0] = row1;
		inputArraySecond[1] = row2;
		inputArraySecond[2] = row3;
		
		Matrix myMatrix1 = new Matrix(inputArray);
		Matrix myMatrix2 = new Matrix(inputArraySecond);
		Matrix theProduct;
		try {
			theProduct = Matrix.multiply(myMatrix1, myMatrix2 );
			assertNull(theProduct);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Test should not have thrown error");
			System.out.println(e.getMessage());
		}
		

		
	}

	public void test_multiply5() {
		Fraction f11 = new Fraction(1,4);
		Fraction f12 = new Fraction(0);
		Fraction f21 = new Fraction(0);
		Fraction f22 = new Fraction(1,4);
		Fraction[][] expected = new Fraction[][]{{f11, f12},{f21, f22}};
		
		int[] row1 = new int[]{1,2};
		int[] row2 = new int[]{3,4};
		
		int[][] inputArray = new int[2][2];
		inputArray[0] = row1;
		inputArray[1] = row2;
		
		Matrix myMatrix = new Matrix(inputArray);
		Matrix theProduct;
		try {
			theProduct = Matrix.multiply(myMatrix, myMatrix );
			checkArrayValues( expected, theProduct.getValues());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Test should not have thrown error");
			System.out.println(e.getMessage());
		}		

		
	}
	
	@Test
	public void test_getInverse1() {
		Fraction f11 = new Fraction(1,2);
		Fraction f12 = new Fraction(0);
		Fraction f21 = new Fraction(0);
		Fraction f22 = new Fraction(1,3);
		Fraction[][] expected = new Fraction[][]{{f11, f12},{f21, f22}};
		
		Matrix invertMe = new Matrix(new int[][]{{2,0},{0,3}});
		try {
			Matrix invertedMatrix;
			invertedMatrix = Matrix.getInverse(invertMe);	
			checkArrayValues( expected, invertedMatrix.getValues());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail("Error thrown");
		}
		
	}	
	
	@Test
	public void test_getInverse2() {
		Fraction f11 = new Fraction(4,5);
		Fraction f12 = new Fraction(-1,5);
		Fraction f21 = new Fraction(-3,5);
		Fraction f22 = new Fraction(2,5);
		Fraction[][] expected = new Fraction[][]{{f11, f12},{f21, f22}};
		
		Matrix invertMe = new Matrix(new int[][]{{2,1},{3,4}});
		try {
			Matrix invertedMatrix;
			invertedMatrix = Matrix.getInverse(invertMe);	
			checkArrayValues( expected, invertedMatrix.getValues());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail("Error thrown");
		}	
	}	
	
	@Test
	public void test_getInverse3() {
		int dimensions = 3;
		
		Matrix expected = Matrix.getIdentity(dimensions);
		
		Matrix invertMe = new Matrix(new int[][]{{2,1,-3},{0,4,-4},{-5,0,1}});
		try {
			Matrix invertedMatrix;
			invertedMatrix = Matrix.getInverse(invertMe);	
			Matrix product = Matrix.multiply(invertMe, invertedMatrix);
			checkArrayValues( product.getValues(), expected.getValues());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail("Error thrown");
		}	
	}	

	@Test
	public void test_getInverse4() {
		int dimensions = 5;
		
		Matrix expected = Matrix.getIdentity(dimensions);
		
		Matrix invertMe = new Matrix(new int[][]{{2,1,-3,3,-1},{0,4,-4,2,1},{-5,0,1,3,2},{0,2,-2,2,1},{4,2,1,-3,-2}});
		try {
			Matrix invertedMatrix;
			invertedMatrix = Matrix.getInverse(invertMe);	
			Matrix product = Matrix.multiply(invertMe, invertedMatrix);
			checkArrayValues( product.getValues(), expected.getValues());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail("Error thrown");
		}	
	}	

	@Test
	public void test_getInverse4b() {
		int dimensions = 3;
		
		Matrix expected = Matrix.getIdentity(dimensions);
		
		Matrix invertMe = new Matrix(new int[][]{{2,1,-3},{0,4,-4},{-5,0,1}});
		try {
			Matrix invertedMatrix;
			invertedMatrix = Matrix.getInverse(invertMe);	
			Matrix product = Matrix.multiply(invertMe, invertedMatrix);
			checkArrayValues( product.getValues(), expected.getValues());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail("Error thrown");
		}	
	}		

	//@Test TODO: reinstate this test and make it work!	@Test
	public void test_getInverse4c() {
		int dimensions = 3;
		
		Matrix expected = Matrix.getIdentity(dimensions);
		
		Matrix invertMe = new Matrix(new int[][]{{2,0,-3},{0,0,-4},{-5,0,1}});
		try {
			Matrix invertedMatrix;
			invertedMatrix = Matrix.getInverse(invertMe);	
			Matrix product = Matrix.multiply(invertMe, invertedMatrix);
			checkArrayValues( product.getValues(), expected.getValues());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail("Error thrown");
		}	
	}	
	
	@Test
	public void test_getInverse5() {
		int dimensions = 2;
		
		Matrix expected = Matrix.getIdentity(dimensions);
		
		Matrix invertMe = new Matrix(new int[][]{{0,2},{2,0}});
		try {
			Matrix invertedMatrix;
			invertedMatrix = Matrix.getInverse(invertMe);	
			Matrix product = Matrix.multiply(invertMe, invertedMatrix);
			checkArrayValues( product.getValues(), expected.getValues());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail("Error thrown");
		}	
	}	
	
	//@Test TODO: reinstate this test and make it work!
	public void test_getInverse6() {
		int dimensions = 5;
		
		Matrix expected = Matrix.getIdentity(dimensions);
		
		Matrix invertMe = new Matrix(new int[][]{{2,1,-3,3,-1},{0,4,-4,2,1},{-5,0,1,3,2},{0,2,-2,1,1},{4,2,1,-3,-2}});
		try {
			Matrix invertedMatrix;
			invertedMatrix = Matrix.getInverse(invertMe);	
			Matrix product = Matrix.multiply(invertMe, invertedMatrix);
			checkArrayValues( expected.getValues(), product.getValues() );
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail("Error thrown");
		}	
	}	

	//@Test TODO: reinstate this test and make it work!@Test
	public void test_getInverse7() {
		int dimensions = 5;
		
		Matrix expected = Matrix.getIdentity(dimensions);
		
		Matrix invertMe = new Matrix(new int[][]{{0,1,-3,3,-1},{0,0,-4,2,1},{-5,3,0,3,2},{0,2,-2,0,1},{4,2,1,-3,0}});
		try {
			Matrix invertedMatrix;
			invertedMatrix = Matrix.getInverse(invertMe);	
			Matrix product = Matrix.multiply(invertMe, invertedMatrix);
			checkArrayValues( expected.getValues(), product.getValues() );
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail("Error thrown");
		}	
	}	
	
	@Test
	public void test_scalarProduct() {
		//Can you see comments
		Fraction f11 = new Fraction(5,2);
		Fraction f12 = new Fraction(7,4);
		Fraction f21 = new Fraction(3);
		Fraction f22 = new Fraction(1,4);
		Fraction[][] expected = new Fraction[][]{{f11, f12},{f21, f22}};
		
		int[] row1 = new int[]{10,7};
		int[] row2 = new int[]{12,1};
		
		int[][] inputArray = new int[2][2];
		inputArray[0] = row1;
		inputArray[1] = row2;
		
		Matrix myMatrix = new Matrix(inputArray);
		
		Matrix theProduct;
		try {
			theProduct = Matrix.scalarProduct(myMatrix, new Fraction(1,4));
			checkArrayValues( expected, theProduct.getValues());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Test should not have thrown error");
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void test_multiplyByCommonDenominator() {
		//Can you see comments
		Fraction f11 = new Fraction(3);
		Fraction f12 = new Fraction(0);
		Fraction f21 = new Fraction(0);
		Fraction f22 = new Fraction(2);
		Fraction[][] expected = new Fraction[][]{{f11, f12},{f21, f22}};
		
		int[] row1 = new int[]{2,0};
		int[] row2 = new int[]{0,3};
		
		int[][] inputArray = new int[2][2];
		inputArray[0] = row1;
		inputArray[1] = row2;
		
		Matrix aMatrix = new Matrix(inputArray);
		Matrix myMatrix;
		try {
			myMatrix = Matrix.getInverse(aMatrix);
			Matrix answer = Matrix.multiplyByCommonDenominator(myMatrix);
			checkArrayValues( expected, answer.getValues());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}	
	private void checkArrayValues(Fraction[][] m1, Fraction[][] m2){
		if(m1==null||m2==null){
			fail("One of these arrays is empty");
		}else if(m1.length!=m2.length||m1[0].length!=m2[0].length){
			fail("Matrices have different dimensions");
		}
		
		for (int row = 0; row < m1.length; row++) {
			for (int col = 0; col < m2[0].length; col++) {
				if(Math.abs(m1[row][col].getDecimal()-m2[row][col].getDecimal())>0.000001){
					fail("Discrepancy at row " + row +  " and col " + col);
				}
			}
		}
	}
}
