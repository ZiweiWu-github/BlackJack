package BlackJack;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class JPanelForButtons extends JPanel {
	private static final long serialVersionUID = 1L;

	private PlayerHitButton playerHitButton;
	private PlayerStandButton playerStandButton;
	private PlayerDoubleDownButton playerDoubleDownButton;
	private PlayerSplitButton playerSplitButton;
	private BlackjackGame g;

	public JPanelForButtons(BlackjackGame g) {
		this.g = g;

		// set gridlayout
		this.setLayout(new GridLayout(0, 1));

		// instantiate buttons
		this.playerHitButton = new PlayerHitButton(g);
		this.playerStandButton = new PlayerStandButton(g);
		this.playerDoubleDownButton = new PlayerDoubleDownButton(g);
		this.playerSplitButton = new PlayerSplitButton(g);
	}

	/**
	 * Removes all buttons and puts them in according to the player's available
	 * options
	 */
	public void refreshButtons() {
		Player p = g.getPlayer();
		this.removeAll();

		if(g.getGameState() == GameState.STARTING) {
			
		}
		else if(g.getGameState() == GameState.PLAYING) {
			this.add(playerHitButton);
			this.add(playerStandButton);
			if (p.canDoubleDown())
				this.add(playerDoubleDownButton);
			if (p.canSplit())
				this.add(playerSplitButton);
		}
		else if(g.getGameState() == GameState.DEALERTURN) {
			
		}
		else if(g.getGameState() == GameState.SCORECOUNT) {
			
		}
		else { //the gameState is END
			
		}
		
		this.revalidate();
		this.repaint();
	}
}
