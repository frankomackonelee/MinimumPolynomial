package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import mathsUtils.Fraction;
import mathsUtils.Simplification;

public class SimplificationTest {
	@Test
	public void printInfo(){
		System.out.println("-----------------------------------------------");
		System.out.println("Now running SimplificationTest.java");
	}

	@Test
	public void test_highestCommonFactor_TwoIntegers() {
		assertEquals(5, Simplification.highestCommonFactor(35, 25));
		assertEquals(14, Simplification.highestCommonFactor(42, 56));
		assertEquals(8, Simplification.highestCommonFactor(112, 40));
		assertEquals(12, Simplification.highestCommonFactor(108, 12));
		assertEquals(12, Simplification.highestCommonFactor(12, 108));
		assertEquals(7, Simplification.highestCommonFactor(7, 7));
		assertEquals(1, Simplification.highestCommonFactor(7, 13));
		assertEquals(1, Simplification.highestCommonFactor(7, -13));
		assertEquals(5, Simplification.highestCommonFactor(25, -30));
		assertEquals(14, Simplification.highestCommonFactor(0, 14));
	}

	@Test
	public void test_highestCommonFactor_IntegerArray() {
	    ArrayList<long[]> list = new ArrayList<long[]>();
	    list.add(new long[]{12,9,13,24});
	    list.add(new long[]{12,6,6,4});
	    list.add(new long[]{12,9,30,24});
	    list.add(new long[]{8,12,400,800});
	    list.add(new long[]{500,105,5,25,100});
	    list.add(new long[]{12,18,60,36});
	    list.add(new long[]{707,70,140});
	    
	    for (int i = 0; i < list.size(); i++) {
	    	assertEquals(i+1, Simplification.highestCommonFactor(list.get(i)));;
		}

	}
	
	@Test
	public void test_lowestCommonMultiple_TwoIntegers() {
		try {
			assertEquals(175, Simplification.lowestCommonMultiple(35, 25));
			assertEquals(108, Simplification.lowestCommonMultiple(108, 12));
			assertEquals(108, Simplification.lowestCommonMultiple(12, 108));
			assertEquals(7, Simplification.lowestCommonMultiple(7, 7));
			assertEquals(91, Simplification.lowestCommonMultiple(7, 13));
			assertEquals(-91, Simplification.lowestCommonMultiple(7, -13));
			assertEquals(0, Simplification.lowestCommonMultiple(0, 14));		
			//The following test will only work for longs
			assertEquals(9999999999L, Simplification.lowestCommonMultiple(100001, 99999));
		} catch (Exception e) {
			// TODO: handle exception
			fail("An exception was thrown when it should not have been");
			System.out.println(e.getMessage());
		}
		
		//Should be 999999999
		try {
			assertEquals(1, Simplification.lowestCommonMultiple(10000000001L, 9999999999L));
			fail("Should see a long overflow here, but no error thrown");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue(true);
			System.out.println("long overflow error should be thrown");
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void test_lowestCommonMultiple_IntegerArray() {
		try {
			ArrayList<long[]> list = new ArrayList<long[]>();
		    list.add(new long[]{2,4,1});
		    list.add(new long[]{2,2,8});
		    list.add(new long[]{12,3,4,6});
		    list.add(new long[]{8,16});	    
		    list.add(new long[]{10,4,2,1});
		    list.add(new long[]{24,3});
		    
		    for (int i = 0; i < list.size(); i++) {
		    	assertEquals((i+1)*4, Fraction.lowestCommonMultiple(list.get(i)));
			}			
		} catch (Exception e) {
			// TODO: handle exception
			fail("An exception was thrown when it should not have been");
			System.out.println(e.getMessage());
		}
		
		//Should be 999999999
		try {
			assertEquals(9999999999L, Fraction.lowestCommonMultiple(new long[]{1,100001, 99999}));
			assertEquals(1, Fraction.lowestCommonMultiple(new long[]{1,10000000001L, 9999999999L}));
			fail("Should see an long overflow here, but no error thrown");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue(true);
			System.out.println("long overflow error should be thrown");
			System.out.println(e.getMessage());
		}		

	}
	@Test
	public void test_divideByHCF(){
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("An exception was thrown when it should not have been");
			System.out.println(e.getMessage());
		}
		ArrayList<long[]> startList = new ArrayList<long[]>();
	    ArrayList<long[]> endList = new ArrayList<long[]>();
	    
	    startList.add(new long[]{12,9,3,24});
	    endList.add(new long[]{4,3,1,8});
	    
	    startList.add(new long[]{12,6,6,15});
	    endList.add(new long[]{4,2,2,5});
	    
	    startList.add(new long[]{12,6,23,15});
	    endList.add(new long[]{12,6,23,15});
	    
	    startList.add(new long[]{91,39,390,1391});
	    endList.add(new long[]{7,3,30,107});
	    
	    startList.add(new long[]{90,900,100,45,35,700});
	    endList.add(new long[]{18,180,20,9,7,140});
	    
	    for (int i = 0; i < startList.size(); i++) {
			assertArrayEquals(endList.get(i), Simplification.divideByHCF(startList.get(i)));
		}
	}

}
