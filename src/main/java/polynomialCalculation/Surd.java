package polynomialCalculation;

public class Surd {
	/*
		for a Surd of the form: 3*2^0/5 + 2^1/5 - 2^2/5 + 2*2^3/5 - 2*2^4/5
		base = 2
		nthPower = 5
		nthPowerMulitples = {3, 1, -1, 2, -2}
	*/

	protected int base, nthPower;
	protected int[] nthPowerMultiples;
	
	public Surd(int base, int nthPower, int[] nthPowerMultiples){
		this.base = base;
		this.nthPower = nthPower;
		if(nthPowerMultiples.length == nthPower){
			this.nthPowerMultiples = nthPowerMultiples;
		}else{
			//TODO: add a message to the error?
			IndexOutOfBoundsException e = new IndexOutOfBoundsException();
			throw e;
		}
	}
	
	//Should be protected, but public to make available to test
	public double getValue(){
		return Evaluate(nthPowerMultiples);
	}
	
	public String stringSurd(){
		String answer = "";
		for (int i = 0; i < nthPower; i++) {
			if (i > 0) {
				answer += " + ";
			}
			answer += nthPowerMultiples[i] + "*" + base + "^(" + i + "/" + nthPower + ")";
		}
		return answer;
	}
	
	private double Evaluate(int[] nthPowerMultiples2){
		double runningTotal = 0;
		
		for (int i = 0; i < nthPowerMultiples2.length; i++) {
			runningTotal += (long)nthPowerMultiples2[i] * Math.pow(base, ((double) i / (double)nthPower));
		}
		return runningTotal;
	}
}
