package BlackJack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


/*
 * JButton to have the player start the game by betting
 */
class GameStartButton extends JButton{
	private static final long serialVersionUID = 1L;

	public GameStartButton(BlackjackGame g) {
		super("Start Game");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				PopUpForStartingBets p = new PopUpForStartingBets(g);
				g.startingBet();
			}
		});
	}
}

/*
 * JButton to have the player accept the insurance bet and moves on to the
 * insurance bet popup
 */
class AcceptInsuranceButton extends JButton{
	private static final long serialVersionUID = 1L;

	public AcceptInsuranceButton(BlackjackGame g) {
		super("<html>Accept<br>Insurance</html>");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				g.getPlayer().setDidInsuranceBet(true);
				@SuppressWarnings("unused")
				PopUpForInsuranceBets p = new PopUpForInsuranceBets(g);
				g.insuranceBet();
			}
		});
	}
}

/*
 * JButton to have the player decline the insurance bet and moves on to the
 * insurance bet check
 */

class DeclineInsuranceButton extends JButton{
	private static final long serialVersionUID = 1L;

	public DeclineInsuranceButton(BlackjackGame g) {
		super("<html>Decline<br>Insurance</html>");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				g.insuranceCheck();
			}
		});
	}
}

/*
 * The four below buttons are for player actions
 */
class PlayerHitButton extends JButton{
	private static final long serialVersionUID = 1L;

	public PlayerHitButton(BlackjackGame g){
		super("Hit");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				g.getPlayer().addCard(g.getDeck().draw());
				g.updatePlayer();
			}
		});
	}
}

class PlayerStandButton extends JButton{
	private static final long serialVersionUID = 1L;

	public PlayerStandButton(BlackjackGame g) {
		super("Stand");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				g.getPlayer().stand();
				g.updatePlayer();
			}
		});
	}
}

class PlayerDoubleDownButton extends JButton{
	private static final long serialVersionUID = 1L;

	public PlayerDoubleDownButton(BlackjackGame g) {
		super("Double Down");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				g.getPlayer().addCard(g.getDeck().draw());
				g.getPlayer().stand();
				g.updatePlayer();
			}
		});
	}
}

class PlayerSplitButton extends JButton{
	private static final long serialVersionUID = 1L;

	public PlayerSplitButton(BlackjackGame g) {
		super("Split");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				g.getPlayer().split();
				g.updatePlayer();
			}
		});
	}
}

/*
 * JButton to have the dealer make the move
 */
class DealerMoveButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	public DealerMoveButton(BlackjackGame g) {
		super("Dealer's Turn");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				g.dealerMakeMove();
			}
		});
	}
}

/*
 * JButton to move on to the score check
 */
class CheckScoreButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	public CheckScoreButton(BlackjackGame g) {
		super("Score Check");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				g.scoreCount();
			}
		});
	}
}

/*
 * JButton for the player to surrender
 */
class SurrenderButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	public SurrenderButton(BlackjackGame g) {
		super("Surrender");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				g.surrender();
			}
		});
	}
}

/*
 * JButton for the player to surrender
 */
class RestartButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	public RestartButton(BlackjackGame g) {
		super("Restart Game");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				g.restart();
			}
		});
	}
}