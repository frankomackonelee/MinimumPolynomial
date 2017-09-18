package mathsUtils;

public class Simplification {
	public static long highestCommonFactor(long integer1, long integer2){
		long high, low;
		long checkHigh = Math.abs(integer1);
		long checkLow = Math.abs(integer2);
		if (checkHigh>=checkLow) {
			high = checkHigh;
			low = checkLow;
		}else{
			low = checkHigh;
			high = checkLow;
		}
		if(high == 0 || low == 0){
			if(high == 0 && low == 0){
				return 1;
			}else{
				return Math.max(high, low);
			}
		}
		long remainder = high % low;
		if(remainder == 0){
			if (low==0) {
				return Math.abs(high);
			}else{
				return Math.abs(low);
			}
		}else{
			return highestCommonFactor(low, remainder);
		}
	}
	
	public static long highestCommonFactor(long[] values){
		if (values.length<2) {
			return -1;
		}
		long hcf = highestCommonFactor(values[0], values[1]);
		for (int i = 2; i < values.length; i++) {
			if(hcf==1){
				break;
			}
			hcf = highestCommonFactor(hcf, values[i]);
		}
		return hcf;	
	}
	
	public static long lowestCommonMultiple(long high, long low) throws LongOverflowException{
		if(highestCommonFactor(high, low) == 0){
			return 0;
		}else{
			long calc = high / highestCommonFactor(high, low);
			calc = CheckedOperations.multiply(low, calc);
			return calc;			
		}
	}
	
	public static long[] divideByHCF(long[] valuesIn){
		long hcf = highestCommonFactor(valuesIn);
		int count = valuesIn.length;
		long[] valuesOut = new long[count];
		for (int i = 0; i < count; i++) {
			valuesOut[i] = valuesIn[i]/hcf;
		}
		return valuesOut;
	}
}
