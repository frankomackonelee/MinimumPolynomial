package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import polynomialCalculation.PowersOfSurd;

public class PowersOfSurdTest {
	@Test
	public void printInfo(){
		System.out.println("-----------------------------------------------");
		System.out.println("Now running PolwersOfSurdTest.java");
	}

	@Test
	public void test_getValuePowerOfX() {
		int numberOfValuesChecked = 0;
		int numberOfSurds = 5;
		PowersOfSurd mySurds[] = new PowersOfSurd[numberOfSurds];
		ArrayList<ArrayList<Double>> group = new ArrayList<ArrayList<Double>>(numberOfSurds);
		
		try {
			mySurds[0] = new PowersOfSurd(5,3,new int[]{0, -3, 1});
			ArrayList<Double> currentSurdPowers = new ArrayList<Double>();
			currentSurdPowers.add(-2.20591);
			currentSurdPowers.add(4.86604);
			currentSurdPowers.add(-10.73405);
			group.add(currentSurdPowers);
			
			mySurds[1] = new PowersOfSurd(5,4,new int[]{1, 0, -2, 0});
			currentSurdPowers = new ArrayList<Double>();
			currentSurdPowers.add(-3.47214);
			currentSurdPowers.add(12.05573);
			currentSurdPowers.add(-41.85913);
			currentSurdPowers.add(145.34058);
			group.add(currentSurdPowers);
			
			mySurds[2] = new PowersOfSurd(5,5,new int[]{2, 1, -1, 1, -1});
			currentSurdPowers = new ArrayList<Double>();
			currentSurdPowers.add(0.47871);
			currentSurdPowers.add(0.22916);
			currentSurdPowers.add(0.1097);
			currentSurdPowers.add(0.05251);
			currentSurdPowers.add(0.02514);
			group.add(currentSurdPowers);
			
			mySurds[3] = new PowersOfSurd(5,5,new int[]{-4, 3, 2, 1, 1});
			currentSurdPowers = new ArrayList<Double>();
			currentSurdPowers.add(10.19692);
			currentSurdPowers.add(103.97724);
			currentSurdPowers.add(1060.24789);
			currentSurdPowers.add(10811.2661);
			currentSurdPowers.add(110241.64774);
			group.add(currentSurdPowers);

			mySurds[4] = new PowersOfSurd(5,6,new int[]{-22, 2, 2, 2, 2, 2});
			currentSurdPowers = new ArrayList<Double>();
			currentSurdPowers.add(2.00269);
			currentSurdPowers.add(4.01076);
			currentSurdPowers.add(8.03231);
			currentSurdPowers.add(16.08623);
			currentSurdPowers.add(32.21572);
			currentSurdPowers.add(64.51807);
			group.add(currentSurdPowers);			

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			fail("An error has been thrown by the PowersOfSurd() constructor when it shouldn't have been.  Fix it");
		}
		
		/*		This test places an upper limit on the sizes of the surd in the sixth power
		 / 		fifth power evaluated correctly...
		  * 	sixth power does not.  Integers in teh PowersOfSurds array should all be divisble by 3.
		  * 	only at the point it falls over do they cease to be!

		 		mySurds[5] = new PowersOfSurd(5,6,new int[]{-33, 3, 3, 3, 3, 3});
				currentSurdPowers = new ArrayList<Double>();
				currentSurdPowers.add(3.00403);
				currentSurdPowers.add(9.02422);
				currentSurdPowers.add(27.10906);
				currentSurdPowers.add(81.43653);
				currentSurdPowers.add(244.63809);
				currentSurdPowers.add(734.90111);
				group.add(currentSurdPowers);
				 */

		
		for (int j = 0; j<mySurds.length; j++) {
			ArrayList<Double> doubleArray = group.get(j);
			for (int i = 0; i < doubleArray.size(); i++) {
				assertEquals(doubleArray.get(i), mySurds[j].getValuePowerOfX(i), 0.00001);
				numberOfValuesChecked++;
			}
		}
		
	}
	
	
	@Test
	public void test_findUpperBound_stressTest(){
		//This shows in this implementation that the maximum value is 
		
		PowersOfSurd thisSurd;
		ArrayList<Double> thisSurdValues = new ArrayList<Double>(5);
		int i = 2;
		try {
			while (i<120) {
				//TODO: some annotation to help understand this. When printed should make sense!
				
				thisSurd = new PowersOfSurd(i, 5, new int[]{i,i,i,i,i});
				//System.out.println("Currently testing: "+ thisSurd.stringSurd());
				double x=0;
				for (int j = 0; j < 5; j++) {
					double myPower = ((double)j/(double)5);
					x += i * Math.pow(i, myPower);
				}
				thisSurdValues.clear();
				for (int k = 1; k < 6; k++) {
					thisSurdValues.add(Math.pow(x, k));
				}
				
				for (int l = 0; l < 5; l++) {
					//System.out.println("Comparing " + thisSurdValues.get(l) + " to " + thisSurd.getValuePowerOfX(l));
					assertEquals(1, thisSurdValues.get(l)/thisSurd.getValuePowerOfX(l), 0.0000000001);
				}		
				i++;
			}				
		} catch (Exception e) {
			if(i==119){
				System.out.println("An error was deliberately thrown in PowersOfSurd() constructor to test limits of int type.  No need to worry");
			}else{
				fail("An unanticipated error has been caught in PowersOfSurd constuctor.  Needs fixing");
			}
		}

	}

}
