import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * This class monitors the game.
 * It contains: a snake, a food.
 * @author Victor
 *
 */
public class Game {
	private Snake _snake = new Snake();
	public static final int timerSpeed = 600;
	private Timer _timer = new Timer(timerSpeed, new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0){
			continueGame();
		}
	});
	
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
	public void startGame(){		
		_snake = new Snake();
		_timer.restart();
		Food.getInstance().updateFood();
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
		if(!_snake.nextMove()){
			_timer.stop();
			System.out.println("Game ends!");
			return;
		}
	}
	
	/**
	 * Getter for getting the snake in this current game.
	 * @return
	 */
	public Snake getSnake(){
		return _snake;
	}
	
	

}
