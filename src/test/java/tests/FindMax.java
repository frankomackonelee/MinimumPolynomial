package tests;

import java.util.Stack;

public class FindMax {
	//used to find the maximum int:
	//for int: 	2147483647			+ 1 = -2147483648
	//			2.1 E9
	//for long:	9223372036854775807 + 1 =  -9223372036854775808
	//			9.2	E18
	static long currentTestInt = 1;
	static long nextJump = 1;
	static Stack<Long> jumps = new Stack<Long>();
	static int i = 0;
	
	public static void main(String[] args) {
		findMax();
		
		System.out.println("Number of iterations: " + i);
		System.out.println("Value of the test integer is: " + currentTestInt);
		System.out.println("When this is added: " + 1);
		System.out.println("You get this: " + (currentTestInt + 1));	
		System.out.println("---------------------");
		System.out.println("From Integer:");
		System.out.println("Max value: " + Integer.MAX_VALUE);
		System.out.println("Min value: " + Integer.MIN_VALUE);
		System.out.println("---------------------");
		System.out.println("From Long:");
		System.out.println("Max value: " + Long.MAX_VALUE);
		System.out.println("Min value: " + Long.MIN_VALUE);
	}
	
	private static void findMax(){
		boolean onTheUp = true;
		while (nextJump>0) {
			
			if (currentTestInt+nextJump>currentTestInt) {
				currentTestInt = currentTestInt+nextJump;
			}else{
				onTheUp = false;
			}
			
			if(onTheUp){				
				nextJump*=2;
			}else{
				nextJump/=2;
			}
			
			i++;
			
		}
	}
	
	private static void findMaxUsingStack(){
		// TODO Auto-generated method stub
		jumps.push(nextJump);

		boolean doDouble = true;
		while (jumps.size()>0) {
			if(currentTestInt + nextJump > currentTestInt){
				currentTestInt = currentTestInt + nextJump;
				if(doDouble){
					jumps.push(nextJump);
					nextJump *=2;		
				}					
			}else{
				nextJump = jumps.pop();
				doDouble = false;
			}
			i++;	
		}
				
	}
}
