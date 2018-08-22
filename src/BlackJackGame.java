import java.util.ArrayList;
import javax.swing.JTextArea;

public class BlackJackGame {
	private PersonPlayer currentPlayer;
	private int currentHandNum = 1;
	private Dealer dealer;
	private Deck deck;
	private ArrayList<PersonPlayer> hands;
	private String dealerHandString = "Dealer's Hand:\n"
	, playerHandString = "Your Hand(s):\n",
	dealerScoreString = "Dealer's Score:\n";
	
	public BlackJackGame() {
		currentPlayer = new PersonPlayer();
		dealer = new Dealer();
		deck = new Deck();
		hands.add(currentPlayer);
	}
	
	public void startGame(JTextArea textArea) {
		this.currentPlayer.addCard(this.deck.draw());
		this.dealer.addCard(this.deck.draw());
		this.currentPlayer.addCard(this.deck.draw());
		this.dealer.addCard(this.deck.draw());
		
		
		this.dealerHandString += dealer.getInitialHandString();
		this.playerHandString += currentPlayer.getHandString();
		
		String s = this.dealerHandString + "\n\n" + this.playerHandString;
		textArea.setText(s);
	}
	
	public String getGameString() {
		String s = "Dealer's hand\n";
//		s+= this.dealer.getHandString() + "\n\n";
//		s+= "Your hand\n";
//		s+= this.currentPlayer.getHandString();
		return s;
	}
	
	public PersonPlayer getPlayer() {
		return this.currentPlayer;
	}
	
	public Dealer getDealer() {
		return this.dealer;
	}
	
	public void dealerMove() {
		while(this.dealer.canHit()) {
			this.dealer.addCard(this.deck.draw());
		}
		this.dealerHandString = "Dealer's Hand:\n" + this.dealer.getHandString(); 
	}
	
//	public boolean hasSplitHands() {
//		return this.splitHands.size() > 0;
//	}
//	
//	public void playSplit() {
//		player = this.splitHands.remove();
//	}
}
