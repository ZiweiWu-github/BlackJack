package BlackJack;
import static java.lang.System.out;


public class BlackjackMain {
	public static void main(String[] arg) {
		BlackjackGame game = new BlackjackGame();
		game.startGame();
		game.startGame();
		out.println(game.getPlayer().getPlayerHandString());
		//out.println(game.getDealer().getInitialDealerString());
		for(int i: game.getPlayer().getBestScores()) {
			out.println(i);
		}
		game.restart();
		out.println(game.getPlayer().getPlayerHandString());
		for(int i: game.getPlayer().getBestScores()) {
			out.println(i);
		}
	}
}
