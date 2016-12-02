import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

public class Frame extends JFrame implements KeyListener{

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
		
		JButton btnStart = new JButton("Start");
		buttonPanel.add(btnStart);
		btnStart.setFocusable(false);
		
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		
		btnStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Game.getInstance().startGame();
			}
			
		});
		
		
	}

	/**
	 * Need to change direction.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
			case KeyEvent.VK_LEFT:
				Game.getInstance().getSnake().toWest();
				break;
			case KeyEvent.VK_RIGHT:
				Game.getInstance().getSnake().toEast();
				break;
			case KeyEvent.VK_UP:
				Game.getInstance().getSnake().toNorth();
				break;
			case KeyEvent.VK_DOWN:
				Game.getInstance().getSnake().toSouth();
				break;
		}
		
	}

	
	/**
	 * Do nothing.
	 */
	@Override
	public void keyReleased(KeyEvent e) {}


	@Override
	public void keyTyped(KeyEvent e) {}
}
