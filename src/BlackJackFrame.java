import javax.swing.JFrame;
import javax.swing.JTextArea;

public class BlackJackFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	
	public BlackJackFrame(String s) {
		super(s);
		textArea = new JTextArea();
	}
}
