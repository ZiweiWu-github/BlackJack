package Blackjack;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class JPanelForGameText extends JPanel{
	private static final long serialVersionUID = 1L;
	private BlackjackGame game;
	private JTextArea gameTextArea;
	
	public JPanelForGameText(BlackjackGame g) {
		this.game = g;
		this.gameTextArea = new JTextArea();
		gameTextArea.setLineWrap(true);
		gameTextArea.setWrapStyleWord(true);
		gameTextArea.setEditable(false);
		this.setLayout(new GridLayout(1, 1));
		this.add(gameTextArea);
	}
	
	/*
	 * Refreshes the text for the game based on the game's state
	 */
	public void refreshText() {
		GameState state = this.game.getGameState();
		switch(state) {
		case DEALERBLACKJACKWIN:
			this.setDealerBJWinText();
			break;
		case DEALERTURN:
			this.setDealerTurnText();
			break;
		case END:
			this.setEndText();
			break;
		case INSURANCE:
			this.setInsuranceText();
			break;
		case PLAYERSURRENDER:
			this.setSurrenderText();
			break;
		case PLAYING:
			this.setPlayingText();
			break;
		case SCORECOUNT:
			this.setScoreCountText();
			break;
		case STARTING:
			this.setStartText();
			break;
		default:
			break;
		}
	}
	
	/*
	 * The below methods are private methods to set the text of the JTextArea
	 * for the game.
	 */
	
	private void setStartText() {
		String t = "Press Start to start the game";
		this.gameTextArea.setText(t);
	}
	
	private void setDealerTurnText() {
		String t = "It's the dealer's turn now!";
		this.gameTextArea.setText(t);
	}
	
	private void setDealerBJWinText() {
		String t = "The dealer won by Blackjack!!!\n\n";
		t += this.getScoreCountString();
		this.gameTextArea.setText(t);
	}
	
	private void setEndText() {
		String t = this.getScoreCountString() + "\n\n";
		HandStatus[] handStatuses = this.game.getPlayer().getHandStates();
		for(int i =0; i< handStatuses.length; i++) {
			HandStatus h = handStatuses[i];
			switch(h) {
			case LOSE:
				t += "Hand #" + (i+1) + " loses!\n\n";
				break;
			case TIE:
				t += "Hand #" + (i+1) + " ties!\n\n";
				break;
			case WIN:
				t += "Hand #" + (i+1) + " wins!\n\n";
				break;
			default:
				break;
			
			}
		}
		this.gameTextArea.setText(t);
	}
	
	private void setInsuranceText() {
		String t = this.getPlayingString() + "\n\n";
		t += "The dealer has offered insurance.\nDo you decline or accept?";
		this.gameTextArea.setText(t);
	}
	
	private void setSurrenderText() {
		String t = "You have surrendered!\n\n";
		this.gameTextArea.setText(t);
	}
	
	/*
	 * This should display the following things:
	 * Dealer's hand and points
	 * Player's hand and points
	 */
	private void setPlayingText() {
		this.gameTextArea.setText(this.getPlayingString());
	}
	
	private String getPlayingString() {
		Dealer d = this.game.getDealer();
		Player p = this.game.getPlayer();
		String t = d.getInitialDealerString();
		t += "\n\n" + p.getPlayerHandString();
		return t;
	}
	
	private void setScoreCountText() {
		String t = this.getScoreCountString();
		this.gameTextArea.setText(t);
	}
	
	private String getScoreCountString() {
		Dealer d = this.game.getDealer();
		Player p = this.game.getPlayer();
		String t = d.getDealerHandString();
		t += "\n\n" + p.getPlayerHandString();
		return t;
	}
}
