package BlackJack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This is the main class used to instantiate the classes to run the game
 * @author Ziwei Wu
 *
 */

public class BlackjackMain {
	private static String saveFileName = "playerInfo.dat";
	private static PlayerInfo playerInfo;
	
	public static void main(String[] arg) {
		try {
			//try to find the file with the player's save
			FileInputStream input = new FileInputStream(saveFileName);
			ObjectInputStream objectInput = new ObjectInputStream(input);
			playerInfo = (PlayerInfo) objectInput.readObject();
			BlackjackGame game = new BlackjackGame(playerInfo);
			GameFrame frame = new GameFrame(game);
			frame.startGame();
			objectInput.close();
		}
		catch(FileNotFoundException e) {
			//If the file is not there, then create a new playerInfo
			playerInfo = new PlayerInfo();
			BlackjackGame game = new BlackjackGame(playerInfo);
			GameFrame frame = new GameFrame(game);
			frame.startGame();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void systemExit() {
		try {
			FileOutputStream outputStream = new FileOutputStream(saveFileName);
			ObjectOutputStream output = new ObjectOutputStream(outputStream);
			output.writeObject(playerInfo);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
