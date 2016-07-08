import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Basic_Calculator extends JFrame
{
	//Create window for calculator to display on

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel formulaLabel;

	//Create a button to activate the calculator

	private JButton calculate;

	//create a text field to hold the output

	private JTextField outputText = new JTextField(20);

	//create a text field to hold the input

	private JTextField formulaTextField; 

	public static void main(String [] args)
	{
		new Basic_Calculator();   
	}
	//constructor to create the basic outline of the calculator

	public Basic_Calculator()
	{
		int windowHeight = 300;
		int windowWidth = 300;

		//construct the calculator itself
		buildCalculator();

		add(panel,BorderLayout.CENTER);

		setTitle("Kakkalate!");
		setSize(windowWidth,windowHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	private void buildCalculator()
	{
		formulaLabel = new JLabel("Enter an arithmetic formula here: ");
		formulaTextField = new JTextField(20);
		calculate = new JButton("Kakkalate!");
		calculate.addActionListener(new calculateListener());

		panel = new JPanel();
		//add the label, text field, and button to the panel
		panel.add(formulaLabel,BorderLayout.NORTH);
		panel.add(formulaTextField,BorderLayout.EAST);
		panel.add(calculate,BorderLayout.CENTER);
		panel.add(outputText,BorderLayout.SOUTH);
	}
	private class calculateListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String input;                //holds the formula the user enters
			double output;               //holds the calculated value

			input = formulaTextField.getText(); //acquire formula from field
			if(validateInput(input) == false)
			{
				JOptionPane.showMessageDialog(null,"Please enter only digits and operators, such as '+','-','/' or '*'");
				formulaTextField.setText("");          
			}
			else                                //if the input passes the validation test, process it through the calculate method
			{
				output = calculate(input);
				outputText.setText("" + output);
			}
		}
		public boolean validateInput(String input)
		{
			boolean retVal = false;

			for(int x = 0; x < input.length(); x++)
			{
				
				if((input.charAt(x) == '0') || (input.charAt(x) == '1') ||				//if the input string contains only numbers and operators
				   (input.charAt(x) == '2') || (input.charAt(x) == '3') ||            	//the method should return true
				   (input.charAt(x) == '4') || (input.charAt(x) == '5') ||
				   (input.charAt(x) == '6') || (input.charAt(x) == '7') ||				//tried to use the isDigit method with no success
				   (input.charAt(x) == '8') || (input.charAt(x) == '9'))				//until I find another way, I have to use this piecemeal approach
				{                                      			
					retVal = true;                      
				}
				else if((input.charAt(x) == '*') ||
						(input.charAt(x) == '/') ||
						(input.charAt(x) == '-') ||
						(input.charAt(x) == '+'))
				{
					retVal = true;
				}
				else
				{
					retVal = false;                     //if the method finds even one character other than a digit or operator
					return retVal;                      //the method terminates and returns false
				}
			}
			return retVal;
		}
		public double calculate(String input)
		{
			if(((input.contains("+")) == false) &&    	   //base case is that the string contains no operators, in which case it simply
					((input.contains("-")) == false) &&    //returns the string as a double
					((input.contains("*")) == false) &&
					((input.contains("/")) == false))
			{ 
				return Double.parseDouble(input);
			}
			if(input.contains("+") == true)
			{
				double retVal = 0;

				String [] tokens = input.split("[+]");  //if the operator is a plus sign, simply tokenize the string, call the method on the tokens,
				for(int x = 0; x < tokens.length; x++)  //and add together the results
				{
					retVal += (calculate(tokens[x]));
				}
				return retVal;                            //this variable should contain the results of all the arithmetic operations performed by
														  //the method
			}
			if(input.contains("-"))
			{
				String [] tokens  = input.split("[-]");
				
				double retVal2 = calculate(tokens[0]);	  //this variable should contain the result of the subtraction	
				
								
				for(int x = 1; x < tokens.length; x++)	  //retVal2 already contains the first token	
				{										  //so long as it goes in order, the values should be correct	
					double one = calculate(tokens[x]);
					retVal2 -= one;
						
				}
				return retVal2;
			}
			if(input.contains("/"))
			{
				String [] tokens = input.split("[/]");
				
				double retVal3 = calculate(tokens[0]);

				for(int x = 1; x < tokens.length; x++)
				{
						double one = calculate(tokens[x]);
						retVal3 = (retVal3/one);
				}
				return retVal3;
			}
			if(input.contains("*"))
			{
				String [] tokens = input.split("[*]");
				double retVal4 = calculate(tokens[0]);

				for(int x = 1; x < tokens.length; x++)
				{
						double one = calculate(tokens[x]);
						retVal4 = (retVal4*one);
				}
				return retVal4;
			}
			else						//this part serves no purpose except to ensure that the method returns a value.
			{
				double notVal = 0;
				return notVal;
			}



		}

	}

}

