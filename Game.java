import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * This class monitors the game.
 * It contains: a snake, a food.
 * @author Victor
 *
 */
public class Game {
	private Snake _snake = new Snake();
	private int _highScore = 0;
	private int _score = 0;
	public static final int minDelay = 100;
	public static final int maxDelay = 1100;
	public static final int defaultDelay = 600;
	private Timer _timer = new Timer(defaultDelay, new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0){
			continueGame();
		}
	});
	private Frame _frame;
	
	private static Game instance;
	
	private Game(){}
	
	public static Game getInstance(){
		if (instance == null){
			instance=new Game();
		}
		return instance;
	}
	
	/**
	 * Game controlling methods.
	 */
	public void startGame(Frame frame){		
		_snake = new Snake();
		_timer.restart();
		Food.getInstance().updateFood();
		_frame = frame;
	}
	
	public void endGame(){
		_timer.stop();
		GridPanel.getInstance().clearAllPoints();
		
	}
	
	public void pauseGame(){
		_timer.stop();
	}
	
	public void resumeGame(){
		_timer.start();
	}
	
	
	
	/**
	 * Check if the game ends here. If not, generate a new food.
	 */
	public void continueGame(){

		switch(_snake.nextMove()){
		    case WON:
				JOptionPane.showMessageDialog(GridPanel.getInstance(), "You have won!", 
						"Won!!!", JOptionPane.INFORMATION_MESSAGE);
				break;
		    case LOST:
				JOptionPane.showMessageDialog(GridPanel.getInstance(), "Your snake died!", 
						"Lost!!!", JOptionPane.INFORMATION_MESSAGE);
				break;
		    case CONTINUE:
		    	return;
		}
		_timer.stop();
		System.out.println("Game ends!");
	}
	
	/**
	 * Getter for getting the snake in this current game.
	 * @return
	 */
	public Snake getSnake(){
		return _snake;
	}
	
	/**
	 * Update speed
	 */
	public void updateSpeed(int delay){
		_timer.setDelay(delay);
	}
	
	/**
	 * Methods that keep track and sets high scores.
	 * @return
	 */
	public int getHighscore(){
		return _highScore;
		
	}
	public void updateHighscore(){
		if (_highScore < _score){
			_highScore = _score;
		}
		updateScoreLabels();
	}
	public void incrementScore(){
		_score++;
		updateScoreLabels();
		updateHighscore();
	}
	public void clearScore(){
		_score = 0;
		updateScoreLabels();
	}
	public int getScore(){
		return _score;
	}
	public void updateScoreLabels(){
		_frame.updateLabels(_score, _highScore);
	}
	

}
