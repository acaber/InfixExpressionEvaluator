import java.util.Stack;
import java.util.StringTokenizer;

public class InfixExpressionEvaluation {

	private String infixExpression;
	
	Stack<String> operandStack = new Stack<String>();
	Stack<String> operatorStack = new Stack<String>();
	
		
	
	public InfixExpressionEvaluation(String expression) {
		this.infixExpression = expression; 
	
	}
	
	public String tokenize() {
		
		StringTokenizer token = new StringTokenizer(infixExpression, "+-*/()", true);
		
		while(token.hasMoreTokens()){
			
			String t = token.nextToken();
			
			if(isInteger(t)) 
				operandStack.push(t);
				
			else if(t.equals("("))
				operatorStack.push(t);
				
			else if(t.equals(")")) {
				while(!operatorStack.peek().equals("(")) {
					
					int firstNum = Integer.parseInt(operandStack.pop());
					int secondNum = Integer.parseInt(operandStack.pop());
					char operator = operatorStack.pop().charAt(0);
					
					operandStack.push(Integer.toString(performCalculation(firstNum, secondNum, operator)));
				}
				
				operatorStack.pop();
			}
			
			else if(t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/")) {
				while(!operatorStack.empty() && operatorPrecedence(t, operatorStack.peek())) {
					
					int firstNum = Integer.parseInt(operandStack.pop());
					int secondNum = Integer.parseInt(operandStack.pop());
					char o = operatorStack.pop().charAt(0);
					
					
					
					operandStack.push(Integer.toString(performCalculation(firstNum, secondNum, o)));
				}
				
				operatorStack.push(t);
				
			}
			
		}
		
		while(!operatorStack.empty()){
			
			int firstNum = Integer.parseInt(operandStack.pop());
			int secondNum = Integer.parseInt(operandStack.pop());
			char operator = operatorStack.pop().charAt(0);
			
			int total = performCalculation(firstNum, secondNum, operator);
			
			operandStack.push(Integer.toString(total));
			
			
		}
		
		return operandStack.pop();
		
	}
	
	public static int performCalculation(int a, int b, char o) {
		
		switch(o) {
		case '+':
			return b + a;
		case '-':
			return b - a;
		case '*':
			return b * a;
		case '/':
			if(a == 0);
				//exception here
			return b / a;
		}
		return 0;
	}

	public boolean operatorPrecedence(String s, String r) {

		if(r.equals("(") || r.equals(")"))
			return false;
		if(s.equals("*") || s.equals("/") && r.equals("+") || r.equals("-"))
			return false;
		else
			return true;
	}
	
	public boolean isInteger(String input) {
		
		System.out.println("integer method");
		
		try {
			Integer.parseInt(input);
			return true;
		} 
		catch(Exception e) {
			return false;
		}
	}
	
	
}
