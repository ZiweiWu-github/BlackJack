package BlackJack;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class JPanelForButtons{
	private JPanel panel;

	private BlackjackGame g;
	private ButtonCreatorForJPanel buttonGet;

	public JPanelForButtons(BlackjackGame g) {
		panel = new JPanel();
		this.g = g;

		// set gridlayout
		panel.setLayout(new GridLayout(0, 1));
		
		//make button creator
		this.buttonGet = new ButtonCreatorForJPanel(g);
	}

	public JPanel getPanel() {
		return this.panel;
	}
	
	/**
	 * Removes all buttons and puts them in according to the player's available
	 * options and the game's current state
	 */
	public void refreshButtons() {
		Player p = g.getPlayer();
		GameState gameState = g.getGameState();
		panel.removeAll();

		if(gameState == GameState.STARTING) {
			panel.add(this.buttonGet.gameStartButton);
		}
		else if(gameState == GameState.INSURANCE) {
			panel.add(this.buttonGet.acceptInsuranceButton);
			panel.add(this.buttonGet.declineInsuranceButton);
		}
		else if(gameState == GameState.PLAYING) {
			if(p.canHit())
				panel.add(buttonGet.playerHitButton);
			panel.add(this.buttonGet.playerStandButton);
			panel.add(this.buttonGet.playerDoubleDownButton);
			if (p.canSplit())
				panel.add(this.buttonGet.playerSplitButton);
			panel.add(this.buttonGet.surrenderButton);
		}
		else if(gameState == GameState.DEALERTURN) {
			panel.add(this.buttonGet.dealerMoveButton);
		}
		else if(gameState == GameState.SCORECOUNT) {
			panel.add(this.buttonGet.checkScoreButton);
		}
		else if(gameState == GameState.MAKINGINSURANCEBET ||
				gameState == GameState.MAKINGSTARTINGBET) {
			//do nothing
		}
		else { //the gameState is END or DEALERBLACKJACKWIN or PLAYERSURRENDER
			panel.add(this.buttonGet.restartButton);
		}
		
		panel.revalidate();
		panel.repaint();
	}
	
	/*
	 * Private class for the horrible mess that is creating the buttons
	 */
	private class ButtonCreatorForJPanel{
		JButton gameStartButton, acceptInsuranceButton, 
		declineInsuranceButton,playerHitButton, playerStandButton, playerDoubleDownButton ,
		playerSplitButton, dealerMoveButton, checkScoreButton, surrenderButton, 
		restartButton;
		
		public ButtonCreatorForJPanel(BlackjackGame g) {
			gameStartButton = ButtonFactory.getGameStartButton(g);
			acceptInsuranceButton = ButtonFactory.getAccpetInsuranceButton(g);
			declineInsuranceButton = ButtonFactory.getDeclineInsuranceButton(g);
			playerHitButton = ButtonFactory.getHitButton(g);
			playerStandButton = ButtonFactory.getStandButton(g);
			playerDoubleDownButton = ButtonFactory.getDoubleDownButton(g);
			playerSplitButton = ButtonFactory.getSplitButton(g);
			dealerMoveButton = ButtonFactory.getDealerMoveButton(g);
			checkScoreButton = ButtonFactory.getCheckScoreButton(g);
			surrenderButton = ButtonFactory.getSurrenderButton(g);
			restartButton = ButtonFactory.getRestartButton(g);
		}
	}
}


