import java.util.Collections;

public class Dealer extends Player{
	
	public Dealer() {
		
	}
	
	public String getInitialHandString() {
		String s = this.getCards().get(0);
		s += " ???";
		return s;
	}
	
	public boolean canHit() {
		if(Collections.max(this.getScores()) > 17)
			return false;
		else
			return true;
	}
}
