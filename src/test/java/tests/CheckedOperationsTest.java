package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.theories.PotentialAssignment;

import mathsUtils.CheckedOperations;

public class CheckedOperationsTest {
	@Test
	public void printInfo(){
		System.out.println("-----------------------------------------------");
		System.out.println("Now running CheckedOperationsTest.java");
	}

	@Test
	public void test_add() {
		
		//These should all fail:
		//2147483647 + 1
		//2100000000 + 50000000
		//2147483647 + 2147483647
		//-2147483648 + -1
		
		//These should not:
		//2147483645 + 2
		checkErrorNotOcurring("add", 2147483647, 2147483647);
		checkErrorNotOcurring("add", 2147483647, 1);
		checkErrorNotOcurring("add", 2100000000, 50000000);
		checkErrorNotOcurring("add", 2147483647, 1);
		checkErrorNotOcurring("add", 2147483646, 2);
		checkErrorNotOcurring("add", -2147483648, -1);
		
		checkErrorNotOcurring("add", 10, 1);
		checkErrorNotOcurring("add", 2147483647, -2147483647);
		checkErrorNotOcurring("add", -1, -2147483646);
		checkErrorNotOcurring("add", -1, 1);
	}

	@Test
	public void test_multiply() {
		
		//These should all fail:
		//2147483647 + 1
		//2100000000 + 50000000
		//2147483647 + 2147483647
		//-2147483648 + -1
		
		//These should not:
		//2147483645 + 2
		checkErrorNotOcurring("multiply", 2147483647, 2);
		checkErrorNotOcurring("multiply", 2147483647, -2);
		checkErrorNotOcurring("multiply", 100000, 100000);
		checkErrorNotOcurring("multiply", -100000, -100000);
		
		checkErrorNotOcurring("multiply", 100, 100);
		checkErrorNotOcurring("multiply", 100, -100);
		checkErrorNotOcurring("multiply", 0, 0);
	}
	
	private void checkErrorOcurring(String operation, long first, long second){
		boolean errorOccurred = true;
		long potentialAnswer;
		try{
			switch (operation) {
			case "add":
				potentialAnswer = CheckedOperations.add(first, second);			
				errorOccurred = false;
				break;

			case "multiply":
				potentialAnswer = CheckedOperations.multiply(first, second);	
				errorOccurred = false;
				break;
				
			default:
				errorOccurred = true;
				break;
			}

		}catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("An error has been thrown deliberately to test CheckedOpperations.add().  No need to worry");
		}
		
		if(!errorOccurred){
			fail("Error not being thrown correctly");
		}
	}

	private void checkErrorNotOcurring(String operation, long first, long second){
		boolean errorOccurred = true;
		long potentialAnswer;
		try{
			switch (operation) {
			case "add":
				potentialAnswer = CheckedOperations.add(first, second);			
				errorOccurred = false;
				break;

			case "multiply":
				potentialAnswer = CheckedOperations.multiply(first, second);	
				errorOccurred = false;
				break;
				
			default:
				errorOccurred = true;
				break;
			}		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if(errorOccurred){
			fail("Error was thrown at an inappropriate moment");
		}
	}
}
