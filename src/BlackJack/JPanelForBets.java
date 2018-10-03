package BlackJack;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
* JPanel used for the player to get input bets
*/
public class JPanelForBets extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel moneyLabel, errorLabel;
	private JTextField moneyField;
	
	public JPanelForBets(){
		this.moneyLabel = new JLabel("Enter Your Bet Here");
		this.moneyField = new JTextField();
		this.errorLabel = new JLabel();
		
		this.setLayout(new GridLayout(3, 1));
		
		JPanel moneyLabelPanel = new JPanel();
		moneyLabelPanel.add(this.moneyLabel);
		
		this.add(moneyLabelPanel);
		
		this.add(this.moneyField);
		
		JPanel errorPanel = new JPanel();
		errorPanel.add(errorLabel);
		this.add(errorPanel);
		
	}
	
	public String getMoneyFieldString() {
		return this.moneyField.getText();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 100);
	}
	
	public void setErrorText(String s) {
		this.errorLabel.setText(s);
	}
} 