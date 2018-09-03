package BlackJack;

public enum Card {
	ACE("Ace", 1), TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5",5), SIX("6",6),
	SEVEN("7",7), EIGHT("8", 8), NINE("9", 9), TEN("10", 10), JACK("JACK", 10),
	QUEEN("QUEEN", 10), KING("KING", 10);
	
	private final String rank;
	private final int value;
	
	Card(String r, int v) {
		this.rank = r;
		this.value = v;
	}
	
	public String getRank() {return this.rank;}
	public int getValue() {return this.value;}
}