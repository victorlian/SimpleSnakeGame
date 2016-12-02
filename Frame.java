import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel gridPanel = GridPanel.getInstance();
	private JPanel buttonPanel;
	public static final int sizeGridRow = 380;
	public static final int sizeGridCol = 380;
	

	/**
	 * Create the frame.
	 */
	public Frame() {
		setTitle("Snake!!!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		gridPanel.setBounds(5, 5, sizeGridRow, sizeGridCol);
		contentPane.add(gridPanel);
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(436, 68, 117, 216);
		contentPane.add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnHeybutton = new JButton("HeyButton");
		buttonPanel.add(btnHeybutton);
	}
}
