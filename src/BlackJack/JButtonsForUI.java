package BlackJack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

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