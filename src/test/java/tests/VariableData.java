package tests;

public class VariableData {
	public int base;
	public int nthPower; 
	public int[] nthPowerMultiples;
	public boolean willFail;
	
	VariableData(int base, int nthPower, int[] nthPowerMultiples, boolean willFail){
		this.base = base;
		this.nthPower = nthPower;
		this.nthPowerMultiples = nthPowerMultiples;
		this.willFail = willFail;
	}
	
	VariableData(int base, int nthPower, int[] nthPowerMultiples){
		this(base, nthPower, nthPowerMultiples, false);
	}
	
}
