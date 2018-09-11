package BlackJack;

import java.util.ArrayList;

public class Dealer {
	private Hand hand = new Hand();
	
	//Adds a card to the dealer's hand
	public void addCard(Card c) {
		this.hand.addCard(c);
	}
	
	//Gets the dealer's hand represented as a String
	public String getDealerHandString() {
		String s = "Dealer's Hand:\n";
		s += this.hand.getHandString();
		return s;
	}
	
	public String getInitialDealerString() {
		String s = "Dealer's Hand:\n";
		ArrayList<Card> c= this.hand.getCards();
		s += c.get(0).getRank() + " ";
		s += "???";
		return s;
	}
	
	//Dealer hits on soft 17
	public boolean canHit() {
		return this.hand.countValue().first() < 17;
	}
	
	//Dealer hits until hard 17 or above
	public void makeMove(Deck d) {
		while(this.canHit()) {
			this.addCard(d.draw());
		}
	}
	
	//Checks if the lowest value is above 21
	public boolean hasBust() {
		return this.hand.countValue().first() > 21;
	}
}
