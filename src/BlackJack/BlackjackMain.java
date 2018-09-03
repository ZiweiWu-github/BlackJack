package BlackJack;
import static java.lang.System.out;

public class BlackjackMain {
	public static void main(String[] arg) {
		Person p = new Person();
		p.addCard(Card.FOUR);
		p.addCard(Card.ACE);
		p.addCard(Card.ACE);
		p.addCard(Card.ACE);
		
		for(Integer i: p.countValues()) {
			out.println(i);
		}
	}
}
