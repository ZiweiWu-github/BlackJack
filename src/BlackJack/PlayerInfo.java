package BlackJack;

import java.io.Serializable;

public class PlayerInfo implements Serializable{
	private static final long serialVersionUID = 2320367489106533425L;
	private int currentAmountOfMoney;
	private int currentBet;
	private final int STARTING_AMOUNT_OF_MONEY = 10000;
	private int currentInsuranceBet;
	
	
	public PlayerInfo() {
		this.currentAmountOfMoney = this.STARTING_AMOUNT_OF_MONEY;
	}
	
	public void makeStartingBet(int betAmount) {
		this.currentBet = betAmount;
	}
	
	public void makeInsuranceBet(int amount) {
		this.currentInsuranceBet = amount;
	}
	
	public String getInfoString() {
		String s = "Current Amount of Money: $" + this.currentAmountOfMoney;
		s += "\nCurrent Bet per hand: $" + this.currentBet;
		return s;
	}
	
	/**
	 * lose money for each hand
	 * @param status
	 */
	public void Surrender(HandStatus[] status) {
		for(int i = 0; i < status.length; i++) {
			this.currentAmountOfMoney -= this.currentBet;
		}
		this.currentBet = 0;
	}
	
	/**
	 * Insurance pays 2:1
	 * @param won: boolean for if the player won the insurance bet
	 */
	public void insuranceBetResult(boolean won) {
		if(won) this.currentAmountOfMoney += (this.currentInsuranceBet * 2);
		else this.currentAmountOfMoney -= this.currentInsuranceBet;
		this.currentInsuranceBet = 0;
	}
	
	/**
	 * If the player gets a perfect Blackjack, then the payout is 3:2 (1.5)
	 * If the player wins without a Blackjack, then the payout is the bet (1)
	 * In a tie, money is neither won nor lost
	 * If the player loses, then s/he loses the bet amount
	 * @param status: an array of HandStatuses 
	 */
	public void startBetResult(HandStatus[] status) {
		for(int i = 0; i < status.length; i++) {
			HandStatus h = status[i];
			switch(h) {
			case LOSE:
				this.currentAmountOfMoney -= this.currentBet;
				break;
			case TIE:
				break;
			case WIN:
				this.currentAmountOfMoney += this.currentBet;
				break;
			case BLACKJACK:
				this.currentAmountOfMoney += (this.currentBet * (3/2));
				break;
			default:
				break;
			
			}
		}
		this.currentBet = 0;
	}
	
	
}
