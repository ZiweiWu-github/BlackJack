package BlackJack;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * This class is used for popups for the starting bet
 * @author Ziwei Wu
 *
 */
public class PopUpForStartingBets extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanelForBets betPanel;
	private BlackjackGame game;
	private JButtonForStartingBets button;
	
 	public PopUpForStartingBets(BlackjackGame game) {
		super("Place Your Bet!!!");
		this.game = game;
		this.betPanel = new JPanelForBets();
		this.button = new JButtonForStartingBets(this);
		
		this.setLayout(new GridLayout(1, 2));
		this.add(this.betPanel);
		this.add(this.button);
		
		this.setSize(300, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	
	
	/*
	 * JButton to tell the PlayerInfo class the bets
	 * Also retrieves the string and check for errors
	 */
	private class JButtonForStartingBets extends JButton{
		private static final long serialVersionUID = 1L;

		JButtonForStartingBets(PopUpForStartingBets popUp){
			super("<html>Enter<br>Your<br>Bet<br></html>");
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
						betPanel.setErrorText("<html><h2 style=\"color:red;\">Not a valid number!</p></html>");
					} 
					catch(NegativeNumberException e1) {
						betPanel.setErrorText("<html><h3 style=\"color:red;\">Cannot be a negative number!</p></html>");
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