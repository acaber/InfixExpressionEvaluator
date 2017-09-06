/**
 * FileName: GUI.java
 * Author: Rebecca Johnson
 * Date: 8/26/2017
 * Description: Creates the GUI, holds the action listener for the
 * 	evaluate button, and catches all DivideByZero exceptions.
 *
 */

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener {
	
	//evaluate button
	private static JButton evaluateBtn;
	
	//text fields
	private static JTextField inputTextField;
	private static JTextField resultTextField;
	
	//labels
	private static JLabel inputJLabel;
	private static JLabel outputJLabel;
	private static JLabel space1;
	private static JLabel space2;
	
	//panel
	private JPanel panel;
	
	//constructor
	public GUI() {
		
		//sets window title
		super("Infix Expression Evaluator");
		
		//calls setFrame() method to set up frame
		setFrame();
		
		//calls setPanel() method to set up panel
		setPanel();
		
		//strings used for GUI display
		String inputString = "Enter Infix Expression";
		String resultString = "Result";
		String spaceString = " ";
		
		//white space to allow for correct formatting of GUI
		space1 = new JLabel(String.format("%50s", spaceString));
		space2 = new JLabel(String.format("%50s", spaceString));
		
		//input label
		inputJLabel = new JLabel(String.format("%15s", inputString));
		
		//output label
		outputJLabel = new JLabel(String.format("%5s", resultString));
		
		//evaluate button 
		evaluateBtn = new JButton("Evaluate");
		evaluateBtn.setToolTipText("Evaluates the expression");
		
		//input text field
		inputTextField = new JTextField(25);
		inputTextField.setEditable(true);
		
		//results text field
		resultTextField = new JTextField(25);
		resultTextField.setEditable(false);
		
		//calls addComponents() method to add components to panel
		addComponents();	
	}
			
	//adds components to panel
	private void addComponents() {
		panel.add(inputJLabel);
		panel.add(inputTextField);
		panel.add(space1);
		panel.add(evaluateBtn);
		panel.add(space2);
		panel.add(outputJLabel);
		panel.add(resultTextField);
	}
	
	
	//sets up the frame
	private void setFrame() {
		setSize(450, 140);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	}
	
	//sets up the panel to be added to the frame
	private void setPanel() {
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		add(panel, BorderLayout.CENTER);
	}
	
	//main method
	public static void main(String[] args) {		
		GUI g = new GUI();
		g.setVisible(true);
	
		//evaluate button action listener
		evaluateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				//sets the expression to the input string variable
				String input = inputTextField.getText();
				
				//creates a new instance of the InfixExpressionEvaluation class
				InfixExpressionEvaluation i = new InfixExpressionEvaluation(input);
				
				//to hold result of expression evaluation
				int result;
				
				try {
					
					//sets the result to the result variable
					result = Integer.parseInt(i.expressionEvaluator());
					
					//displays result to the result text field
					resultTextField.setText(Integer.toString(result));
					
					//catches divide by zero exception
				} catch (DivideByZero d) {
					
					//displays error message
					JOptionPane.showMessageDialog(null, 
							"Error! \nCan not divide by zero! \n" + 
					d.getInvalidExpression() + " is an invalid expression.");
				}
		   }
		});		
	}

	//needed to extend ActionListener class
	public void actionPerformed(ActionEvent e) {}
}
