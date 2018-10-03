package BlackJack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonFactory{
	
	/*
	 * JButton to have the player start the game by betting
	 */
	public static JButton getGameStartButton(BlackjackGame g) {
		JButton b = new JButton("Start Game");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				PopUpForStartingBets p = new PopUpForStartingBets(g);
				g.startingBet();
			}
		});
		return b;
	}
	
	/*
	 * Button to have player accept the insurance bet
	 */
	public static JButton getAccpetInsuranceButton(BlackjackGame g) {
		JButton b = new JButton("<html>Accept<br>Insurance</html>");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				g.getPlayer().setDidInsuranceBet(true);
				@SuppressWarnings("unused")
				PopUpForInsuranceBets p = new PopUpForInsuranceBets(g);
				g.insuranceBet();
			}
		});
		return b;
	}
	
	/*
	 * Button to have the player decline the insurance bet
	 */
	public static JButton getDeclineInsuranceButton(BlackjackGame g) {
		JButton b = new JButton("<html>Decline<br>Insurance</html>");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				g.insuranceCheck();
			}
		});
		return b;
	}
	
	/*
	 * JButton to have the player hit the hand
	 */
	public static JButton getHitButton(BlackjackGame g) {
		JButton b = new JButton("Hit");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				g.getPlayer().addCard(g.getDeck().draw());
				g.updatePlayer();
			}
		});
		return b;
	}
	
	
	/*
	 * Button to have the player stand
	 */
	public static JButton getStandButton(BlackjackGame g) {
		JButton b = new JButton("Stand");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				g.getPlayer().stand();
				g.updatePlayer();
			}
		});
		return b;
	}
	
	/*
	 * Button to have the player double down
	 */
	public static JButton getDoubleDownButton(BlackjackGame g) {
		JButton b = new JButton("Double Down");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				g.getPlayer().addCard(g.getDeck().draw());
				if(!g.getPlayer().getHasStood()) {
					g.getPlayer().stand();
				}
				g.getPlayerInfo().doubleDown();
				g.updatePlayer();
			}
		});
		return b;
	}
	
	/*
	 * Button to split the player's hand
	 */
	public static JButton getSplitButton(BlackjackGame g) {
		JButton b = new JButton("Split");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				g.getPlayer().split(g.getDeck());
				g.updatePlayer();
			}
		});
		return b;
	}
	
	/*
	 * Button to have the dealer make their turn
	 */
	public static JButton getDealerMoveButton(BlackjackGame g) {
		JButton b = new JButton("Dealer's Turn");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				g.dealerMakeMove();
			}
		});
		return b;
	}
	
	/*
	 * Button to begin score check
	 */
	public static JButton getCheckScoreButton(BlackjackGame g) {
		JButton b = new JButton("Score Check");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				g.scoreCount();
			}
		});
		return b;
	}
	
	/*
	 * Button for the player to surrender
	 */
	public static JButton getSurrenderButton(BlackjackGame g) {
		JButton b = new JButton("Surrender");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				g.surrender();
			}
		});
		return b;
	}
	
	/*
	 * Button to restart the game
	 */
	public static JButton getRestartButton(BlackjackGame g) {
		JButton b = new JButton("Restart Game");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				g.restart();
			}
		});
		return b;
	}
}