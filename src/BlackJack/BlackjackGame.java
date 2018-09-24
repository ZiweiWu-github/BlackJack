package BlackJack;

import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class contains all the objects of the 'model' for MVC
 * and controls the flow of the game
 * @author Ziwei Wu
 *
 */
public class BlackjackGame {
	private Player player;
	private Dealer dealer;
	private Deck deck;
	private GameState state;
	private PlayerInfo playerInfo;
	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();
	
	public BlackjackGame(PlayerInfo p) {
		this.playerInfo = p;
		this.restart();
	}
	
	/*
	 * The three methods below are the getters for the model
	 */
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Dealer getDealer() {
		return this.dealer;
	}
	
	public Deck getDeck() {
		return this.deck;
	}
	
	public GameState getGameState() {
		return this.state;
	}
	
	public PlayerInfo getPlayerInfo() {
		return this.playerInfo;
	}
	
	/*
	 * The three methods below are for changing and notifying the listeners
	 */
	
	public void addChangeListener(ChangeListener c) {
		this.listeners.add(c);
	}
	
	public void removeChangeListener(ChangeListener c) {
		this.listeners.remove(c);
	}
	
	private void notifyListeners() {
		for(ChangeListener c: this.listeners) {
			c.stateChanged(new ChangeEvent(this));
		}
	}
	
	/*
	 * The below methods are methods that influence the game model and
	 * should notify all listeners.
	 */
	
	/*
	 * This method restarts the game and prompts the player to
	 * press the button to actually start the game by making a bet
	 */
	public void restart(){
		this.player = new Player();
		this.dealer = new Dealer();
		this.deck = new Deck();
		this.state = GameState.STARTING;
		this.notifyListeners();
	}
	
	/*
	 * This method sets the gamestate to starting
	 */
	public void startingBet() {
		this.state = GameState.MAKINGSTARTINGBET;
		this.notifyListeners();
	}
	
	/*
	 * Dealer and player draw 2 cards at the start
	 * If the dealer has a 10 or an Ace facing up, dealer will check for Blackjack
	 * But the dealer gives an insurance bet if the Ace is showing
	 * If Blackjack, then the game ends
	 * Otherwise, the game continues as normal
	 */
	public void startGame() {
		this.player.addCard(this.deck.draw());
		this.dealer.addCard(this.deck.draw());
		this.player.addCard(this.deck.draw());
		this.dealer.addCard(this.deck.draw());
		if(this.dealer.canOfferInsurance()) {
			this.state = GameState.INSURANCE;
		}
		else if(this.dealer.hasBlackjack()) {
			this.state = GameState.DEALERBLACKJACKWIN;
			this.player.stand();
			this.scoreCount();
		}
		else{
			this.state = GameState.PLAYING;
		}
		this.notifyListeners();
	}
	
	/*
	 * When the player surrenders, then the state goes to the surrender state
	 */
	public void surrender() {
		this.state= GameState.PLAYERSURRENDER;
		this.playerInfo.Surrender(this.player.getHandStates());
		this.notifyListeners();
	}
	
	/*
	 * This sets the gamestate to making insurance bet
	 */
	public void insuranceBet() {
		this.state = GameState.MAKINGINSURANCEBET;
		this.notifyListeners();
	}
	
	/*
	 * If blackjack, then check whether or not the player did the insurance bet
	 * If the player did, then they win the insurance bet but lose the starting bet
	 * If not blackjack, then the game resumes as normal
	 */
	public void insuranceCheck() {
		if(this.dealer.hasBlackjack()) {
			this.player.stand();
			this.playerInfo.Surrender(this.player.getHandStates());
			if(this.player.getDidInsuranceBet()) {
				this.player.setInsuranceWon(true);
				this.playerInfo.insuranceBetResult(true);
			}
			this.state = GameState.DEALERBLACKJACKWIN;
			this.scoreCount();
		}
		else {
			if(this.player.getDidInsuranceBet()) {
				this.playerInfo.insuranceBetResult(false);
			}	
			this.state = GameState.PLAYING;
		}
		this.notifyListeners();
	}
	
	/*
	 * This method doesn't really need to do anything other than check then if it's the
	 * dealer's turn
	 */
	public void updatePlayer() {
		if(!this.player.hasAdditionalHands()) {
			this.state = GameState.DEALERTURN;
		}
		this.notifyListeners();
	}
	
	/*
	 * The dealer does what it does and moves on to the score count 
	 */
	public void dealerMakeMove() {
		this.dealer.makeMove(this.deck);
		this.state = GameState.SCORECOUNT;
		this.notifyListeners();
	}
	
	/*
	 * This method checks the scores of the player's hands and then
	 * moves on to end the game
	 */
	public void scoreCount() {
		int[] playerScores = this.player.getBestScores();
		int dealerScore = this.dealer.getBestScore();
		boolean[] Is10Ace = this.player.getIs10Ace();
		
		for(int i =0; i<playerScores.length; i++) {
			int currentHandScore = playerScores[i];
			if(currentHandScore < 22) { //if the hand has not bust
				if(dealerScore > 21 && currentHandScore != 21) {
					this.player.setWinStatusForHand(i, HandStatus.WIN);
				}
				else if(currentHandScore == dealerScore) {
					this.player.setWinStatusForHand(i, HandStatus.TIE);
				}
				else if(currentHandScore == 21) {
					//A 10-Ace hand after splitting does not count as blackjack
					if(i==0) {
						this.player.setWinStatusForHand(i, HandStatus.BLACKJACK);
					}
					else {
						if(Is10Ace[i]) {
							this.player.setWinStatusForHand(i, HandStatus.WIN);
						}
						else {
							this.player.setWinStatusForHand(i, HandStatus.BLACKJACK);
						}
					}
				}
				else if(currentHandScore > dealerScore) {
					this.player.setWinStatusForHand(i, HandStatus.WIN);
				}
			}
		}
		this.playerInfo.startBetResult(this.player.getHandStates());
		
		if(this.state != GameState.DEALERBLACKJACKWIN) {
			this.state = GameState.END;
		}
		
		this.notifyListeners();
	}
	
}

enum GameState{ 
	STARTING, INSURANCE, DEALERBLACKJACKWIN, PLAYING, DEALERTURN, SCORECOUNT, 
	END, PLAYERSURRENDER, MAKINGSTARTINGBET, MAKINGINSURANCEBET
}
