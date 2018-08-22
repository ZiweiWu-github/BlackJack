import java.util.ArrayList;
import java.util.Collections;

public abstract class Player {
	private ArrayList<String> cards = new ArrayList<String>();
	
	public Player() {
		
	}
	
	public ArrayList<String> getCards(){
		return this.cards;
	}
	
	public int getHandSize() {
		return this.cards.size();
	}
	
	public void addCard(String c) {
		this.cards.add(c);
	}
	
	public String getHandString() {
		String handCards = "";
		for(String s: this.cards) {
			handCards += s + " ";
		}
		return handCards;
	}
	
	public ArrayList<Integer> getScores(){
		return BlackJackMain.addPoints(cards);
	}
	
	public String getScoreString() {
		String score = "";
		ArrayList<Integer> scores = this.getScores();
		score += scores.get(0);
		for(int i =1; i<scores.size(); i++) {
			score += " or " + scores.get(i);
		}
		return score;
	}
	
	public boolean hasBust() {
		if(Collections.max(this.getScores()) > 21) {
			return false;
		}
		else return true;
	}
	
}

enum Moves{
	HIT, STAND, SPLIT, SURRENDER, DOUBLE_DOWN
}

enum gameState{
	LOSE, WIN, TIE, PLAYING
}

enum PlayerState{
	STILL_IN, BUST
}
