package BlackJack;

import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		this.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {}
			
			@Override
			public void windowIconified(WindowEvent e) {}
			
			@Override
			public void windowDeiconified(WindowEvent e) {}
			
			@Override
			public void windowDeactivated(WindowEvent e) {}
			
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?",
						"Quitting?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.YES_OPTION) {
					BlackjackMain.systemExit();
					System.exit(0);
				}
			}
			@Override
			public void windowClosed(WindowEvent e) {}
			
			@Override
			public void windowActivated(WindowEvent e) {}
		});
	}
	
	public void startGame() {
		this.buttonPanel.refreshButtons();
		this.textPanel.refreshText();
	}
}
