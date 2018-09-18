package Blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	public Deck() {
		this.reshuffle();
	}
	
	private void reshuffle() {
		for(Card c: Card.values()) {
			this.cards.add(c);
			this.cards.add(c);
			this.cards.add(c);
			this.cards.add(c);
		}
	}
	
	public Card draw() {
		int randomDraw = new Random().nextInt(this.cards.size());
		Card c = this.cards.get(randomDraw);
		this.cards.remove(randomDraw);
		return c;
	}
}
