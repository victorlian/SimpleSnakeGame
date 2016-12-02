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
	private Snake snake = new Snake();
	private Point food;
	public static final int timerSpeed = 500;
	private Timer _timer = new Timer(timerSpeed, new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0){
			nextMove();
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
	
	public void startGame(){		
		_timer.start();
	}
	
	public void nextMove(){
		if(!snake.nextMove()){
			_timer.stop();
			System.out.println("Game ends!");
		}
	}
	
	/**
	 * Getter for getting the snake in this current game.
	 * @return
	 */
	public Snake getSnake(){
		return snake;
	}
	
	

}
