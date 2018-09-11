package BlackJack;

public class BlackjackGame {
	private Player player;
	private Dealer dealer;
	private Deck deck;
	private GameState state;
	
	public BlackjackGame() {
		this.restart();
	}
	
	public void restart(){
		this.player = new Player();
		this.dealer = new Dealer();
		this.deck = new Deck();
		this.state = GameState.STARTING;
	}
	
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
	
	public void startGame() {
		this.player.addCard(this.deck.draw());
		this.dealer.addCard(this.deck.draw());
		this.player.addCard(this.deck.draw());
		this.dealer.addCard(this.deck.draw());
		this.state = GameState.PLAYING;
	}
	
	public void updatePlayer() {
		if(!this.player.hasAdditionalHands()) {
			this.state = GameState.DEALERTURN;
		}
	}
	
	public void dealerMakeMove() {
		this.dealer.makeMove(this.deck);
		this.state = GameState.SCORECOUNT;
	}
	
	//TODO: do the thing the method is supposed to do
	public void scoreCount() {
		//int[] playerScores = this.player.getBestScores();
		//determine who wins and create a string to give to the text area
		this.state = GameState.END;
	}
	
}

enum GameState{
	STARTING, PLAYING, DEALERTURN, SCORECOUNT, END
}
