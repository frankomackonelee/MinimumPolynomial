package polynomialCalculation;

import mathsUtils.CheckedOperations;

public class PowersOfSurd extends Surd{
	//[powerOfx][numeratorOfPower]
	protected long[][] powersOfX;

	public PowersOfSurd(int base, int nthPower, int[] nthPowerMultiples) throws Exception {
		super(base, nthPower, nthPowerMultiples);
		// TODO Auto-generated constructor stub
		powersOfX = new long[nthPower][nthPower];
		//TODO: I'm sure there's a neater way to cast from int[] to long[]...:
		for (int i = 0; i < nthPowerMultiples.length; i++) {
			powersOfX[0][i] = (long)nthPowerMultiples[i];
		}
		for (int i = 1; i < nthPowerMultiples.length; i++) {
			try {
				powersOfX[i] = multiplyByX(powersOfX[i-1]);				
			} catch (Exception e) {
				throw e;
			}
			//System.out.println(getMaxIntegerInArray(powersOfX[i]));
		}
	}
	
	public double getValuePowerOfX(int power){
		return Evaluate(powersOfX[power]);
	}
	
	private double Evaluate(long[] nthPowerMultiples2){
		double runningTotal = 0;
		for (int i = 0; i < nthPowerMultiples2.length; i++) {
			runningTotal += nthPowerMultiples2[i]*Math.pow(base, (double) i / nthPower);
		}
		return runningTotal;
	}
	
	protected long[] multiplyByX(long[] coefficientsOfEachNumerator) throws Exception{
		if (coefficientsOfEachNumerator.length!=nthPower) {
			//todo: check this exception works
			IndexOutOfBoundsException e = new IndexOutOfBoundsException();
			throw e;
		}
		long[] answer = new long[nthPower];
		
		int row, col;
		for(int i = 0; i < 2*nthPower - 1; i++){
			int limit = Math.min(i, nthPower-1);
			row = limit;
			col = i - row;
			int powerNumeratorModN = i % nthPower;
			int extraMultiple = (int) Math.pow(base, (i-powerNumeratorModN)/nthPower);
			while (col<=limit) {
				long productToAdd = coefficientsOfEachNumerator[col]*nthPowerMultiples[row]*extraMultiple;
				try{
					answer[powerNumeratorModN] = CheckedOperations.add(answer[powerNumeratorModN], productToAdd);
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					throw e;
				}
				row--;
				col++;
			}
		}
		return answer;
	}
	
}
