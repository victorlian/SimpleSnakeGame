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
	private Food _food;
	public static final int timerSpeed = 1000;
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
	
	public void startGame(){		
		_timer.start();
		_food = Food.getInstance();
	}
	
	//Check if the game ends here. If not, generate a new food.
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
