package mathsUtils;

public class Matrix {
	//Class variables
	
	//Instance variables
	private Fraction[][] values;
	private int rowCount;
	private int colCount;
	private Fraction scalarApplied;
	
	//constructor
	Matrix(int rowCount, int colCount){
		this.rowCount = rowCount;
		this.colCount = colCount;
		this.scalarApplied = new Fraction (1);
		values = new Fraction[rowCount][colCount];		
	}
	
	public Matrix(int[][] theseValues){
		this(theseValues.length, theseValues[0].length);
		for (int row = 0; row < rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				values[row][col] = new Fraction(theseValues[row][col]);
			}
		}
	}

	public Matrix(long[][] theseValues) {
		this(theseValues.length, theseValues[0].length);
		for (int row = 0; row < rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				values[row][col] = new Fraction(theseValues[row][col]);
			}
		}
	}

	//Instance methods
	public Fraction[][] getValues(){
		return values;
	}
	
	public Fraction getValue(int row, int col){
		return this.values[row][col];
	}
	
	public Fraction getScalarl(){
		return this.scalarApplied;
	}
	
	public Matrix makeCopy(){
		//TODO: check whether there is an inbuilt method of object to do this
		Matrix theCopy = new Matrix(rowCount, colCount);
		for (int row = 0; row < rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				theCopy.values[row][col] = new Fraction(this.values[row][col].getNumerator(), this.values[row][col].getDenominator());
			}
		}
		return theCopy;
	}

	private void divideRow(int whichRow, Fraction divideByThis) throws Exception{
		//TODO: logit
		//System.out.println("Dividing row " +  whichRow + " by " + divideByThis.getNumerator() + "/" + divideByThis.getDenominator());
		for (int col = 0; col < colCount; col++) {
			this.values[whichRow][col].divide(divideByThis);
		}
	}
	
	private void subtractMultiplesOfOneRowFromOther(Fraction thisMany, int ofThisRow, int fromThisRow) throws Exception{
		//TODO: logit
		//System.out.println("Subtracting " + thisMany.getString() + " of row " + ofThisRow + " from " + fromThisRow);
		for (int col = 0; col < colCount; col++) {
			Fraction toAdd = Fraction.multiply(thisMany, this.values[ofThisRow][col]);
			this.values[fromThisRow][col].subtract(toAdd);
		}		
	}
	
	private Fraction[] getRow(int row) {
		return values[row];
	}

	private Fraction[] getCol(int col) {
		Fraction[] answer = new Fraction[rowCount];
		for (int i = 0; i < rowCount; i++) {
			answer[i] = values[i][col];
		}
		return answer;
	}

	//Static methods
	public static Matrix multiply(Matrix m1, Matrix m2) throws Exception{
		if(m1.colCount!=m2.rowCount){
			return null;
		}else{
			int answerRowCount = m1.rowCount;
			int answerColCount = m2.colCount;
			Matrix answer = new Matrix(answerRowCount,answerColCount);
			for (int row = 0; row < answerRowCount; row++) {
				for (int col = 0; col < answerColCount; col++) {
					answer.values[row][col] = sumProduct(m1.getRow(row), m2.getCol(col));
				}
			};	
			return answer;
		}
	}
	
	public static Matrix getIdentity(int rowsAndCols){
		Matrix answer = new Matrix(rowsAndCols, rowsAndCols);
		for (int i = 0; i < rowsAndCols; i++) {
			for (int j = 0; j < rowsAndCols; j++) {
				answer.values[i][j] = (i==j ? new Fraction(1) : new Fraction(0));	
			}
		}
		return answer;
	}
	
	public static Matrix getInverse(Matrix invertMe) throws Exception{
		//TODO: the pass by value of address may mean that the matrix passed gets fucked.
		//need to look at this in testing and correct it.
		Matrix leftMatrix = invertMe.makeCopy();
		if(leftMatrix.colCount!=leftMatrix.rowCount){
			Exception e = new Exception("Unable to invert a matrix which is not square");
			throw e;
		}
		Matrix rightMatrix = getIdentity(leftMatrix.rowCount);
		for (int col = 0; col < leftMatrix.colCount; col++) {
			//TODO: Need to cope with zero in lead diagonal
			if(leftMatrix.values[col][col].getNumerator()==0){
				for (int y = 0; y < leftMatrix.rowCount; y++) {
					if(leftMatrix.values[y][col].getNumerator()!=0){
						Fraction howMany = new Fraction(-1);
						leftMatrix.subtractMultiplesOfOneRowFromOther(howMany, y, col);	
						rightMatrix.subtractMultiplesOfOneRowFromOther(howMany, y, col);
					}
				}
			}
			
			Fraction divideBy = new Fraction(leftMatrix.values[col][col].getNumerator(),leftMatrix.values[col][col].getDenominator());			
			leftMatrix.divideRow(col, divideBy);
			rightMatrix.divideRow(col, divideBy);
			
			for (int row = 0; row < leftMatrix.rowCount; row++) {
				if(row!=col){
					Fraction thisMany = new Fraction(leftMatrix.values[row][col].getNumerator(), leftMatrix.values[row][col].getDenominator());
					if(thisMany.getNumerator()!=0){
						leftMatrix.subtractMultiplesOfOneRowFromOther(thisMany, col, row);	
						rightMatrix.subtractMultiplesOfOneRowFromOther(thisMany, col, row);
					}
				}
			}			
		}
		
		return rightMatrix;
	}
	
	public static Matrix scalarProduct(Matrix toMultiply, Fraction scalar) throws Exception{
		Matrix answer = toMultiply.makeCopy();
		for (int row = 0; row < answer.rowCount; row++) {
			for (int col = 0; col < answer.colCount; col++) {
				answer.values[row][col].multiply(scalar);
			}
		}
		answer.scalarApplied = scalar;
		return answer;
	}
	
	public static Matrix multiplyByCommonDenominator(Matrix matrixWithFractions) throws Exception{
		//TODO: check that all denominators are 1. If not throw exception
		Matrix matrixWithIntegers = matrixWithFractions.makeCopy();
		Fraction scalar = Fraction.commonDenominator(matrixWithIntegers.values);
		return Matrix.scalarProduct(matrixWithIntegers, scalar);
	}

	private static Fraction sumProduct(Fraction[] currentRow, Fraction[] currentCol) throws Exception{
		//TOdo to avoid error due to a by ref and alteration;
		
		if(currentRow.length!=currentCol.length){
			return null;
		}else{
			Fraction answer = new Fraction(0);
			for (int i = 0; i < currentCol.length; i++) {
				answer.add(Fraction.multiply(currentRow[i], currentCol[i]));
			}
			return answer;
		}
	}
	
}
