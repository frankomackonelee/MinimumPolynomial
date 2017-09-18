package mathsUtils;

public class CheckedOperations {
	//TODO: keep the long ones!
	public static long add(long first, long second) throws LongOverflowException{
		long intAnswer = first + second;
		double dblAnswer = (double)first + (double)second;
		double error = (double)intAnswer / dblAnswer - 1;
		if(Math.abs(error)>0.1){
			LongOverflowException e = new LongOverflowException("long type is not sufficient for this addition step: " + first + " + " + second);
			throw e;
		}
		return intAnswer;
	}
	
	//TODO: the int checks should be removed when long is implemented
	public static long multiply(long first, long second) throws LongOverflowException {
		long intAnswer = first * second;
		double dblAnswer = (double)first * (double)second;
		double error = (double)intAnswer / dblAnswer - 1;
		if(Math.abs(error)>0.1){
			LongOverflowException e = new LongOverflowException("long type is not sufficient for this multiplication step: " + first + " x " + second);
			throw e;
		}
		return intAnswer;		
	}	
}
