package BlackJack;

/**
 * This is the main class used to instantiate the classes to run the game
 * @author Ziwei Wu
 *
 */
public class BlackjackMain {
	public static void main(String[] arg) {
		BlackjackGame game = new BlackjackGame();
		GameFrame frame = new GameFrame(game);
		frame.startGame();
	}
}
