package BlackJack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PopUpForStartingBets extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanelForBets betPanel;
	private BlackjackGame game;

	public PopUpForStartingBets(BlackjackGame game) {
		super("Place Your Bet!!!");
		this.game = game;
		betPanel = new JPanelForBets();
	}
	
	
	/*
	 * JButton to tell the PlayerInfo class the bets
	 */
	private class JButtonForStartingBets extends JButton{
		JButtonForStartingBets(){
			
		}
	}
}

/*
 * JPanel used for the player to get bets from
 */
class JPanelForBets extends JPanel{
	private static final long serialVersionUID = 1L;
	
	/*
	 * TODO: need 3 things: JLabel, JTextField, and empty JLabel for errors
	 * Getters and error checkers?
	 */
	JPanelForBets(){
		
	}
}