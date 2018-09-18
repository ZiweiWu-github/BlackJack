package Blackjack;

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
		s += this.hand.getHandString() + "\n";
		s += this.hand.valueString();
		return s;
	}
	
	//When starting, the second card is hidden
	public String getInitialDealerString() {
		String s = "Dealer's Hand:\n";
		ArrayList<Card> c= this.hand.getCards();
		s += c.get(0).getRank() + " ";
		s += "???";
		return s;
	}
	
	/*
	 * Returns the best score of the dealer's hand
	 */
	public int getBestScore() {
		Integer score = this.hand.countValue().lower(22);
		if(score!=null) {
			return score;
		}
		else {
			return this.hand.countValue().higher(21);
		}
	}
	
	//dealer offers insurance if the face-up card is an Ace
	public boolean canOfferInsurance() {
		return this.hand.getCards().get(0) == Card.ACE;
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
	
	//checks if dealer has a blackjack or not
	public boolean hasBlackjack() {
		return this.hand.countValue().lower(22) == 21;
	}
}
