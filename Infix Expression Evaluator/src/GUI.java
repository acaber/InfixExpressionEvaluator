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
	
		
		evaluateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				String input = inputTextField.getText();
				InfixExpressionEvaluation i = new InfixExpressionEvaluation(input);
				Double result = Double.parseDouble(i.tokenize());
				resultTextField.setText(String.format("%.0f", result));
		   }
		});	
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
