package BlackJack;

import java.util.ArrayList;

public class Person {

	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<Integer> values = new ArrayList<Integer>();
	
	public Person() {}
	
	public void addCard(Card c) {
		this.hand.add(c);
	}
	
	public ArrayList<Integer> countValues(){
		this.values.clear();
		countValuesHelper(0,0);
		return values;
	}
	
	private void countValuesHelper(int curValue, int curPos) {
		if(curPos == this.hand.size()) this.values.add(curValue);
		else if(this.hand.get(curPos) == Card.ACE) {
			countValuesHelper(curValue + Card.ACE.getValue(), curPos + 1 );
			countValuesHelper(curValue + 11, curPos + 1);
		}
		else {
			countValuesHelper(curValue + this.hand.get(curPos).getValue(), curPos + 1);
		}
	}
}