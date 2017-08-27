/**
 * FileName: DivideByZero.java
 * Author: Rebecca Johnson
 * Date: 8/26/2017
 * Description: Checked exception that is thrown when the user
 * 	attempts to divide by zero.
 *
 */
public class DivideByZero extends Exception{

	//fields to hold numerator and denominator
	private int numerator;
	private int denominator;
	
	//constructor
	public DivideByZero(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	//returns the invalid expression
	public String getInvalidExpression() {
		return String.format("%d / %d", numerator, denominator);
	}
	
	
	
	
}
