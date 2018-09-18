package Blackjack;

import java.util.ArrayList;
import java.util.TreeSet;

public class Hand {

	private ArrayList<Card> hand = new ArrayList<Card>();
	private TreeSet<Integer> values = new TreeSet<Integer>();
	private boolean handChangedAfterCounting = false;
	private HandStatus winStatus = HandStatus.LOSE;

	public Hand() {
		this.values.add(0); //the starting value is 0
	}
	
	/*
	 * The two methods below are for checking if the hand wins
	 * against the dealer's hand or not. By default, the win status is
	 * set to lose. This is so that the game only has to check for wins and ties.
	 */
	
	public HandStatus getWinStatus() {
		return this.winStatus;
	}
	
	public void setWinStatus(HandStatus win) {
		this.winStatus = win;
	}
	
	/**
	 * Adds a card to the hand and sets handChangedAfterCounting to true
	 * @param c
	 */
	public void addCard(Card c) {
		this.hand.add(c);
		this.handChangedAfterCounting = true;
	}
	
	public void removeCard(int value) {
		this.hand.remove(value);
		this.handChangedAfterCounting = true;
	}
	
	/**
	 * Returns a String that represents the cards in the hand
	 * @return
	 */
	public String getHandString() {
		String s = "";
		for(Card c: hand) {
			s += c.getRank() + " ";
		}
		return s.trim();
	}
	
	/**
	 * Returns the ArrayList of cards
	 * @return
	 */
	public ArrayList<Card> getCards(){
		return this.hand;
	}
	
	/**
	 * Returns a String of all possible values of the hand
	 * @return
	 */
	public String valueString() {
		String s = "";
		countValue();
		for(Integer i: this.values) {
			s += i + " or ";
		}
		if(s.isEmpty()) return s;
		else return s.substring(0, s.length()-3);
	}

	/**
	 * Returns a set of all possible values of the hand
	 * @return
	 */
	public TreeSet<Integer> countValue(){
		if(!handChangedAfterCounting) return this.values;
		else{
			this.countValuesHelper();
			this.handChangedAfterCounting = false;
			return this.values;
		}
	}
	
	/**
	 * Used to count the values in the hand
	 */
	private void countValuesHelper() {
		this.values.clear();
		this.values.add(0);
		for(int i =0; i<this.hand.size(); i++) {
			Card currentCard = this.hand.get(i);
			TreeSet<Integer> tempTreeSet = new TreeSet<Integer>();
			for(Integer j: this.values) {
				if(currentCard == Card.ACE) {
					tempTreeSet.add(j + 1);
					tempTreeSet.add(j+11);
				}
				else {
					tempTreeSet.add(j + currentCard.getValue());
				}
			}
			this.values = tempTreeSet;
		}
	}
}

enum HandStatus{
	WIN, LOSE, TIE;
}