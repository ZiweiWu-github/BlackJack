package BlackJack;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * This class is used for popups for the starting bet
 * @author Ziwei Wu
 *
 */

public class PopUpForStartingBets{
	private JPanelForBets betPanel;
	private BlackjackGame game;
	private JButtonForStartingBets button;
	
 	public PopUpForStartingBets(BlackjackGame game) {
 		JFrame frame = new JFrame("Place Your Bet!!!");
		this.game = game;
		this.betPanel = new JPanelForBets();
		this.button = new JButtonForStartingBets(frame);
		
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = 3;
		
		frame.add(this.betPanel, c);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0.5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 2;
		frame.add(this.button,c);
		
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	
	/*
	 * JButton to tell the PlayerInfo class the bets
	 * Also retrieves the string and check for errors
	 */
	private class JButtonForStartingBets extends JButton{
		private static final long serialVersionUID = 1L;

		JButtonForStartingBets(JFrame popUp){
			super("Enter Your Bet");
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Integer money = Integer.parseInt(betPanel.getMoneyFieldString());
						if(money < 0) {
							throw new NegativeNumberException();
						}
						PlayerInfo pI = game.getPlayerInfo();
						pI.makeStartingBet(money);
						game.startGame();
						popUp.dispose();
					}
					catch(NumberFormatException m) {
						betPanel.setErrorText("<html><p><font size = \"5\" color=\"red\">Not a valid number!</font></p></html>");
					} 
					catch(NegativeNumberException e1) {
						betPanel.setErrorText("<html><p><font size = \"5\" color=\"red\">Cannot be a negative number!</font></p></html>");
					}
				}
			});
		}
	}
}

class NegativeNumberException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public NegativeNumberException() {
		super("Cannot be a negative number!");
	}
}