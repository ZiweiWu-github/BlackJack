package BlackJack;

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
		case MAKINGINSURANCEBET:
			this.setBetText();
			break;
		case MAKINGSTARTINGBET:
			this.setBetText();
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
	
	private void setBetText() {
		String t = "Make the bet before continuing";
		this.gameTextArea.setText(t);
	}
	
	private void setDealerTurnText() {
		String t = "It's the dealer's turn now!";
		this.gameTextArea.setText(t);
	}
	
	private void setDealerBJWinText() {
		String t = "The dealer won by Blackjack!!!\n\n";
		t += this.getScoreCountString() + "\n\n";
		if(this.game.getPlayer().getDidInsuranceBet()) {
			if(this.game.getPlayer().getDidInsuranceWon()) {
				t += "Insurance bet winning: $" + this.game.getPlayerInfo().getInsuranceBet() *2;
			}
		}
		this.gameTextArea.setText(t.trim());
	}
	
	private void setEndText() {
		String t = this.getScoreCountString() + "\n\n";
		HandStatus[] handStatuses = this.game.getPlayer().getHandStates();
		String positiveMoney = "(+$" + this.game.getPlayerInfo().getCurrentBet() + ")";
		String negativeMoney = "(-$" + this.game.getPlayerInfo().getCurrentBet() + ")";
		String bjMoney  = "(+$" + this.game.getPlayerInfo().getCurrentBet() * 3 /2 + ")";
		for(int i =0; i< handStatuses.length; i++) {
			HandStatus h = handStatuses[i];
			switch(h) {
			case LOSE:
				t += "Hand #" + (i+1) + " loses! " + negativeMoney + "\n\n";
				break;
			case TIE:
				t += "Hand #" + (i+1) + " ties!\n\n";
				break;
			case WIN:
				t += "Hand #" + (i+1) + " wins! " + positiveMoney + "\n\n";
				break;
			case BLACKJACK:
				t += "Hand #" + (i+1) + " has a BLACKJACK!!! " + bjMoney +"\n\n";
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
		PlayerInfo i = this.game.getPlayerInfo();
		String t = i.getInfoString()+ "\n\n";
		t += d.getInitialDealerString();
		t += "\n\n" + p.getPlayerHandString();
		t += "\n\n" + this.getInsuranceLostString();
		return t.trim();
	}
	
	private void setScoreCountText() {
		String t = this.getPlayingString();
		this.gameTextArea.setText(t);
	}
	
	private String getScoreCountString() {
		Dealer d = this.game.getDealer();
		Player p = this.game.getPlayer();
		PlayerInfo i = this.game.getPlayerInfo();
		String t = i.getInfoString()+ "\n\n";
		t += d.getDealerHandString();
		t += "\n\n" + p.getPlayerHandString();
		t += "\n\n" + this.getInsuranceLostString();
		return t.trim();
	}
	
	private String getInsuranceLostString() {
		String t = "";
		Player p = this.game.getPlayer();
		PlayerInfo i = this.game.getPlayerInfo();
		if(p.getDidInsuranceBet()) {
			if(!p.getDidInsuranceWon()) {
				t += "Insurance Bet Lost: $" + i.getInsuranceBet();
			}
		}
		return t;
	}
}
