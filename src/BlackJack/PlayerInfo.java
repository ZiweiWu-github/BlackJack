package BlackJack;

import java.io.Serializable;

public class PlayerInfo implements Serializable{
	private int currentAmountOfMoney;
	private int currentBet;
	private final int STARTING_AMOUNT_OF_MONEY = 10000;
	private int insuranceBet;
	
	public PlayerInfo() {
		this.currentAmountOfMoney = this.STARTING_AMOUNT_OF_MONEY;
	}
	
	
}
