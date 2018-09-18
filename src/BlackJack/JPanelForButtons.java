package BlackJack;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class JPanelForButtons extends JPanel {
	private static final long serialVersionUID = 1L;

	private BlackjackGame g;
	private ButtonCreatorForJPanel buttonGet;

	public JPanelForButtons(BlackjackGame g) {
		this.g = g;

		// set gridlayout
		this.setLayout(new GridLayout(0, 1));
		
		//make button creator
		this.buttonGet = new ButtonCreatorForJPanel(g);
	}

	/**
	 * Removes all buttons and puts them in according to the player's available
	 * options and the game's current state
	 */
	public void refreshButtons() {
		Player p = g.getPlayer();
		GameState gameState = g.getGameState();
		this.removeAll();

		if(gameState == GameState.STARTING) {
			this.add(this.buttonGet.gameStartButton);
		}
		else if(gameState == GameState.INSURANCE) {
			this.add(this.buttonGet.acceptInsuranceButton);
			this.add(this.buttonGet.declineInsuranceButton);
		}
		else if(gameState == GameState.PLAYING) {
			this.add(buttonGet.playerHitButton);
			this.add(this.buttonGet.playerStandButton);
			if (p.canDoubleDown())
				this.add(this.buttonGet.playerDoubleDownButton);
			if (p.canSplit())
				this.add(this.buttonGet.playerSplitButton);
			this.add(this.buttonGet.surrenderButton);
		}
		else if(gameState == GameState.DEALERTURN) {
			this.add(this.buttonGet.dealerMoveButton);
		}
		else if(gameState == GameState.SCORECOUNT) {
			this.add(this.buttonGet.checkScoreButton);
		}
		else { //the gameState is END or DEALERBLACKJACKWIN or PLAYERSURRENDER
			this.add(this.buttonGet.restartButton);
		}
		
		this.revalidate();
		this.repaint();
	}
	
	/*
	 * Private class for the horrible mess that is creating the buttons
	 */
	private class ButtonCreatorForJPanel{
		GameStartButton gameStartButton;
		AcceptInsuranceButton acceptInsuranceButton;
		DeclineInsuranceButton declineInsuranceButton;
		PlayerHitButton playerHitButton;
		PlayerStandButton playerStandButton;
		PlayerDoubleDownButton playerDoubleDownButton;
		PlayerSplitButton playerSplitButton;
		DealerMoveButton dealerMoveButton;
		CheckScoreButton checkScoreButton;
		SurrenderButton surrenderButton;
		RestartButton restartButton;
		
		public ButtonCreatorForJPanel(BlackjackGame g) {
			gameStartButton = new GameStartButton(g);
			acceptInsuranceButton = new AcceptInsuranceButton(g);
			declineInsuranceButton = new DeclineInsuranceButton(g);
			playerHitButton = new PlayerHitButton(g);
			playerStandButton = new PlayerStandButton(g);
			playerDoubleDownButton = new PlayerDoubleDownButton(g);
			playerSplitButton = new PlayerSplitButton(g);
			dealerMoveButton = new DealerMoveButton(g);
			checkScoreButton = new CheckScoreButton(g);
			surrenderButton = new SurrenderButton(g);
			restartButton = new RestartButton(g);
		}
	}
}


