
public class PersonPlayer extends Player{
	private final int MAX_HAND_SIZE = 5;
	
	public PersonPlayer() {
		
	}
	
	public boolean canHit() {
		return this.getHandSize() < MAX_HAND_SIZE;
	}
	
	public boolean canSplit() {
		if(this.getHandSize() == 2) {
			if(this.getCards().get(0).equals("Ace")) {
				if(this.getCards().get(1).equals("Ace")) {
					return true;
				}
				else return false;
			}
			else if(BlackJackMain.cardsToPoints.get(this.getCards().get(0)) == 
					BlackJackMain.cardsToPoints.get(this.getCards().get(1))){
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public PersonPlayer splitHand() {
		PersonPlayer newPlayer = new PersonPlayer();
		String otherHand = this.getCards().get(0);
		this.getCards().remove(0);
		newPlayer.addCard(otherHand);
		return newPlayer;
	}
}
