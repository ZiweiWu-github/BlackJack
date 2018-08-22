import java.util.ArrayList;

public class Deck {
	private String[] cardTypes = new String[] {"Ace", "2", "3", "4", "5", "6", "7",
			"8", "9", "10", "Jack", "Queen", "King"};
	private ArrayList<String> cards = new ArrayList<String>();
	
	public Deck() {
		this.shuffle();
	}
	
	public void shuffle() {
		cards.clear();
		for(int i =0; i<cardTypes.length; i++) {
			for(int j = 0; j<4; j++) {
				cards.add(cardTypes[i]);
			}
		}
	}
	
	public String draw() {
		String s = null;
		int drawInt = BlackJackMain.getRandomNumberInRange(0, cards.size()-1);
		s = cards.get(drawInt);
		cards.remove(drawInt);
		return s;
	}
}
