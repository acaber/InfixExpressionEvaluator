/**
 * FileName: InfixExpressionEvaluation.java
 * Author: Rebecca Johnson
 * Date: 8/26/2017
 * Description: Uses a StringTokenizer to break up the expression and
 * 	sorts the data into two stacks: operandStack and operatorStack. 
 * 	Calculates the result of the expression while maintaining proper
 * 	operator precedence.
 *
 */

import java.util.Stack;
import java.util.StringTokenizer;

public class InfixExpressionEvaluation {

	//holds expression
	private String infixExpression;
	
	//stack for operands
	Stack<String> operandStack = new Stack<String>();
	
	//stack for operators
	Stack<String> operatorStack = new Stack<String>();
	
	//constructor
	public InfixExpressionEvaluation(String expression) {
		
		//stores the expression to the infixExpression field
		this.infixExpression = expression; 
	}
	
	//evaluates the expression
	public String expressionEvaluator() throws DivideByZero {
		
		//tokenizes the expression
		StringTokenizer token = new StringTokenizer(infixExpression, "+-*/()", true);
		
		//while there are more tokens
		while(token.hasMoreTokens()){
			
			//sets the value of the next token to t
			String t = token.nextToken();
			
			//checks if the token is an integer
			if(isInteger(t))
				
				//pushes integer to the operand stack
				operandStack.push(t);
				
			//checks if the token is a left parentheses
			else if(t.equals("("))
				
				//pushes left parentheses to the operator stack
				operatorStack.push(t);
				
			//checks if the token is a right parentheses
			else if(t.equals(")")) {
				
				//while the top of the operator stack is not a left parentheses
				while(!operatorStack.peek().equals("("))
					
					//calls pushToOperandStack method
					pushToOperandStack();
				
				//discards the top element in the operator stack
				operatorStack.pop();
			}
			
			//checks if the token is a plus sign, minus sign, multiplication sign, or division sign
			else if(t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/")) {
				
				//while the operator stack is not empty and validates the operator precedence
				while(!operatorStack.empty() && operatorPrecedence(operatorStack.peek()) >= operatorPrecedence(t))
					
					//calls pushToOperandStack method
					pushToOperandStack();
				
				//pushes current operator to stack
				operatorStack.push(t);	
			}	
		}
		
		//while operator stack is not empty
		while(!operatorStack.empty())
			
			//calls pushToOperandStack method
			pushToOperandStack();
		
		//returns the top value in the operand stack which holds the result
		return operandStack.pop();	
	}
	
	//pushes calculations to the operand stack
	public void pushToOperandStack() throws DivideByZero {
		
		//pushes the performed calculation to the operand stack
		operandStack.push(Integer.toString(performCalculation(Integer.parseInt(operandStack.pop()),
				Integer.parseInt(operandStack.pop()), operatorStack.pop().charAt(0))));
	}
	
	//performs the calculations in the expression
	public static int performCalculation(int a, int b, char o) throws DivideByZero{
		
		switch(o) {
		
		//checks if the operator is a plus sign
		case '+':
			return (b + a);
			
		//checks if the operator is a minus sign
		case '-':
			return (b - a);
			
		//checks if the operator is a multiplication sign
		case '*':
			return (b * a);
			
		//checks if the operator is a division sign
		case '/':
			if(a == 0)
				//creates a new instance of DivideByZero exception
				throw new DivideByZero(b, a);
			return (b / a);
		}
		return 0;
	}

	//checks the precedence order of operators
	public int operatorPrecedence(String operator) {
		
		//retrieves the operator and stores it to a char variable
		char op = operator.charAt(0);

		//gives * and / a precedence of 2
		if(op == '*'|| op == '/')
			return 2;
		
		//gives + and - a precedence of 1
		else if(op == '+' || op == '-')
			return 1;
		
		//otherwise, the precedence is 0
		else
			return 0;
	}	
	
	//checks if token is an integer
	public boolean isInteger(String input) {
		
		try {
			//tries to convert the input to an integer
			Integer.parseInt(input);
			return true;
		} 
		catch(Exception e) {
			return false;
		}
	}
}
