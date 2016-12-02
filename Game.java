import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * This class monitors the game.
 * It contains: a gridPanel, a snake, a food.
 * @author Victor
 *
 */
public class Game {
	private GridPanel grid = GridPanel.getInstance();
	private Snake snake = new Snake();
	private Point food;
	private Timer _timer = new Timer(1000, new ActionListener(){
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
	
	

}
