package Blackjack;

import java.io.Serializable;

//TODO: Serialize and Deserialize this in the main class
public class PlayerInfo implements Serializable{
	private static final long serialVersionUID = 1L;
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
		s += "Current Bet: $" + this.currentBet;
		return s;
	}
	
	/**
	 * Insurance pays 2:1
	 * @param won: boolean for if the player won the insurance bet
	 */
	public void insuranceBetResult(boolean won) {
		if(won) this.currentAmountOfMoney += (this.currentInsuranceBet * 2);
		else this.currentAmountOfMoney -= this.currentInsuranceBet;
	}
	
	/**
	 * If the player gets a perfect Blackjack, then the payout is 3:2 (1.5)
	 * If the player wins without a Blackjack, then the payout is the bet (1)
	 * In a tie, money is neither won nor lost
	 * If the player loses, then s/he loses the bet amount
	 * @param status: an array of HandStatuses 
	 * @param bestScores: an array of the best points from the player's hand(s)
	 */
	public void startBetResult(HandStatus[] status, int[] bestScores) {
		for(int i = 0; i < status.length; i++) {
			HandStatus h = status[i];
			switch(h) {
			case LOSE:
				this.currentAmountOfMoney -= this.currentBet;
				break;
			case TIE:
				break;
			case WIN:
				int playerScore = bestScores[i];
				if(playerScore == 21) {
					this.currentAmountOfMoney += (this.currentBet * (3/2));
				}
				else {
					this.currentAmountOfMoney += this.currentBet;
				}
				break;
			default:
				break;
			
			}
		}
	}
}
