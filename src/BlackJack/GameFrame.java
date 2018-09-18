package BlackJack;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanelForButtons buttonPanel;
	private JPanelForGameText textPanel;
	
	public GameFrame(BlackjackGame game) {
		super("Blackjack");
		
		//instantiate JPanels
		buttonPanel = new JPanelForButtons(game);
		textPanel = new JPanelForGameText(game);
		
		game.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				buttonPanel.refreshButtons();
				textPanel.refreshText();
			}
		});
		
		
		//add JPanels
		this.setLayout(new GridLayout(1,2));
		this.add(textPanel);
		this.add(buttonPanel);
		
		this.setSize(600, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void startGame() {
		this.buttonPanel.refreshButtons();
		this.textPanel.refreshText();
	}
}

enum GameState{
	STARTING, INSURANCE, DEALERBLACKJACKWIN, PLAYING, DEALERTURN, SCORECOUNT, END, PLAYERSURRENDER
}