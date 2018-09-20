package BlackJack;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * This class is used for popups for insurance bets
 * @author Ziwei Wu
 *
 */
public class PopUpForInsuranceBets extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanelForBets betPanel;
	private BlackjackGame game;
	private ButtonForInsuranceBets betButton;

	public PopUpForInsuranceBets(BlackjackGame g) {
		this.betPanel = new JPanelForBets();
		this.game = g;
		this.betButton = new ButtonForInsuranceBets(this);
		
		this.setLayout(new GridLayout(1, 2));
		this.add(this.betPanel);
		this.add(this.betButton);
		
		this.setSize(300, 200);
		this.setVisible(true);
	}
	
	
	private class ButtonForInsuranceBets extends JButton{
		private static final long serialVersionUID = 1L;

		ButtonForInsuranceBets(PopUpForInsuranceBets popUp){
			super("<html>Enter<br>Your<br>Bet<br></html>");
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Integer money = Integer.parseInt(betPanel.getMoneyFieldString());
						PlayerInfo pI = game.getPlayerInfo();
						pI.makeInsuranceBet(money);
						popUp.dispose();
					}
					catch(NumberFormatException m) {
						betPanel.setErrorText("<html><h2 style=\"color:red;\">Not a valid number!</p></html>");
					}
				}
			});
		}
		
	}
}
