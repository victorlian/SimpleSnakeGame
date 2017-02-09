import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

import javax.swing.JButton;

public class Frame extends JFrame implements KeyListener, ChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel gridPanel = GridPanel.getInstance();
	private JPanel controlPanel;
	private JLabel scoreLabel = new JLabel();
	private JLabel highScoreLabel = new JLabel();
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
		
		controlPanel = new JPanel();
		controlPanel.setBounds(436, 68, 117, 316);
		contentPane.add(controlPanel);
		controlPanel.setLayout(new GridLayout(8, 0, 0, 0));
		

		addButtons();
		controlPanel.add(new JPanel());
		addSlider();
		controlPanel.add(new JPanel());
		addLabels();
		
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

	@Override
	public void stateChanged(ChangeEvent e) {
		
		JSlider slider = (JSlider) e.getSource();
		System.out.println("speed change!" + slider.getValue());
		Game.getInstance().updateSpeed(slider.getValue());
		
	}
	
	/**
	 * Private helper method for adding all the buttons to the Panel.
	 */
	private void addButtons() {
		JButton btnStart = new JButton("Start");
		JButton btnEnd = new JButton("End");
		JButton btnPause = new JButton("Pause");

		controlPanel.add(btnStart);
		btnStart.setFocusable(false);
		controlPanel.add(btnEnd);
		btnEnd.setFocusable(false);
		controlPanel.add(btnPause);
		btnPause.setFocusable(false);
		
		btnStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Game.getInstance().startGame(Frame.this);
				btnStart.setEnabled(false);
				btnEnd.setEnabled(true);
				btnPause.setEnabled(true);
			}
			
		});
		btnEnd.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Game.getInstance().endGame();
				btnEnd.setEnabled(false);
				btnStart.setEnabled(true);
				btnPause.setText("Pause");
				btnPause.setEnabled(false);
			}
			
		});
		btnPause.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (btnPause.getText().equals("Pause")){
					Game.getInstance().pauseGame();
					btnPause.setText("Resume");
				}
				else {
					Game.getInstance().resumeGame();
					btnPause.setText("Pause");
				}
			}
			
		});
	}
	
	/**
	 * Private helper method for adding the JSlider.
	 */
	private void addSlider(){
		//Create the slider
		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, Game.minDelay, Game.maxDelay, Game.defaultDelay);
		speedSlider.addChangeListener(this);
		speedSlider.setMajorTickSpacing(200);
		speedSlider.setPaintTicks(true);

		//Create the label table
		Hashtable <Integer, JLabel>labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put( new Integer( Game.maxDelay ), new JLabel("Slow") );
		labelTable.put( new Integer( Game.defaultDelay ), new JLabel("Normal") );
		labelTable.put( new Integer( Game.minDelay ), new JLabel("Fast") );
		speedSlider.setLabelTable( labelTable );

		speedSlider.setPaintLabels(true);
		
		controlPanel.add(speedSlider);
		speedSlider.setFocusable(false);			
		
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
	}
	
	/**
	 * Private helper method for adding the score JLabel.
	 */
	private void addLabels(){
		int score = Game.getInstance().getScore();
		int highScore = Game.getInstance().getHighscore();
		
		scoreLabel.setText("current score: " + score);
		highScoreLabel.setText("highscore: " + highScore);
		
		controlPanel.add(scoreLabel);
		controlPanel.add(highScoreLabel);
	}
	
	public void updateLabels(int score, int highScore) {
		scoreLabel.setText("current score: " + score);
		highScoreLabel.setText("highscore: " + highScore);
	}

}
