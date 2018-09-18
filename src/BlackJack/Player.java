package BlackJack;

import java.util.ArrayList;

/**
 * The Player class stores the current hand(s) and options the player
 * can make.
 * @author Ziwei Wu
 *
 */
public class Player {
	
	private ArrayList<Hand> hands = new ArrayList<Hand>();
	private int currentHandNumber = 0;
	private boolean didInsuranceBet = false;
	private boolean wonInsuranceBet = false;
	
	public Player() {
		hands.add(new Hand());
	}
	
	/**
	 * Changes a hand's win status
	 * @param handNum: position of the arraylist
	 * @param status: the HandStatus
	 */
	public void setWinStatusForHand(int handNum, HandStatus status) {
		this.hands.get(handNum).setWinStatus(status);
	}
	
	/**
	 * Adds a card to the current hand if it can
	 * Automatically stands when busted
	 * @param c
	 */
	public void addCard(Card c) {
		if(this.currentHandNumber < this.hands.size()) {
			this.hands.get(currentHandNumber).addCard(c);
			if(!this.canHit()) this.stand();
		}
	}
	
	/*
	 * The three methods below are to check the insurance bet for the player
	 */
	
	public void setInsuranceWon(boolean b) {
		this.wonInsuranceBet = b;
	}
	
	public boolean getDidInsuranceWon() {
		return this.wonInsuranceBet;
	}
	
	public boolean getDidInsuranceBet() {
		return this.didInsuranceBet;
	}
	
	public void setDidInsuranceBet(boolean b) {
		this.didInsuranceBet = b;
	}
	
	/**
	 * Returns a string representing the player's hands
	 * @return
	 */
	public String getPlayerHandString() {
		String s = "";
		for(int i =0; i<hands.size(); i++) {
			Hand tempHand = hands.get(i);
			s += "Hand #" + (i+1);
			if(this.currentHandNumber == i) s+= "(Now Playing)";
			s += "\n";
			s += "Cards: " + tempHand.getHandString() + "\n";
			s += "Points: " + tempHand.valueString() + "\n\n";
		}
		if(this.didInsuranceBet) {
			if(this.wonInsuranceBet) {
				s+= "Insurance Bet Won";
			}
			else {
				s+= "Insurance Bet Lost";
			}
		}
		return s.trim();
	}
	
	//This method returns the current hand without the long get 
	private Hand getCurrentHand() {return this.hands.get(currentHandNumber);}
	
	public int getCurrentHandNumber() {return this.currentHandNumber +1;}
	
	/*
	 * The below methods are used to check the player's options for available moves
	 */
	
	//The player can hit as many times as they want to until they bust
	public boolean canHit() {
		if(hasAdditionalHands())
			return this.getCurrentHand().countValue().first() < 22;
		else
			return false;
	}
	
	//The player can double down only if it is the first turn
	public boolean canDoubleDown() {
		if(this.hasAdditionalHands() && this.hands.size()==1)
			return this.getCurrentHand().getCards().size() == 2;
		else
			return false;
	}
	
	//Can only split on the first turn, allows for multiple splits too
	public boolean canSplit() {
		if(this.canDoubleDown() && 
				this.getCurrentHand().getCards().get(0).getValue() == this.getCurrentHand().getCards().get(1).getValue()) {
			return true;
		}
		else return false;
	}
	
	/*
	 * The below methods are for changing the player's hands
	 */
	
	//The 'hit' option is already represented by the 'addCard' method
	//The double down method consists of a hit and a stand
	
	//Increases the currentHandNumber
	public void stand() {
		this.currentHandNumber++;
	}
	
	//Check if dealer goes or if player plays additional hand
	public boolean hasAdditionalHands() {
		return this.currentHandNumber < this.hands.size();
	}
	
	/**
	 * Splits the player's hand in two
	 */
	public void split() {
		Hand h = new Hand();
		h.addCard(this.getCurrentHand().getCards().get(0));
		this.getCurrentHand().removeCard(0);;
		this.hands.add(h);
	}
	
	/**
	 * Returns an array of ints that are the hand's best scores
	 * @return
	 */
	public int[] getBestScores() {
		int[] scores = new int[this.hands.size()];
		for(int i =0; i<this.hands.size(); i++) {
			Integer best = this.hands.get(i).countValue().lower(22);
			if(best != null) {
				scores[i] = best;
			}
			else {
				scores[i] = this.hands.get(i).countValue().higher(21);
			}
		}
		return scores;
	}
	
	/**
	 * Returns an array of the hands' win status
	 * @return
	 */
	public HandStatus[] getHandStates() {
		HandStatus[] states = new HandStatus[this.hands.size()];
		for(int i = 0; i<this.hands.size(); i++) {
			Hand tempHand = this.hands.get(i);
			states[i] = tempHand.getWinStatus();
		}
		return states;
	}
	
}
