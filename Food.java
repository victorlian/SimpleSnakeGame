import java.util.Random;

public class Food {
	private Point point;
	private Game game = Game.getInstance();
	private Snake snake = game.getSnake();
	private static Food instance;
	
	
	private Food(){
		updateFood();
	}
	
	public static Food getInstance(){
		if (instance == null){
			instance=new Food();
		}
		return instance;
	}
	
	/**
	 * This method finds a new empty location to put the food in and updates that point.
	 */
	public void updateFood(){
		Random rand = new Random();
		
		if (snake.isFull()){
			throw new RuntimeException("Full already, should not updateFood");
		}
		
		do {
			int row = rand.nextInt(GridPanel.rows);
			int col = rand.nextInt(GridPanel.cols);
			point = GridPanel.getInstance().getPointAt(row, col);
		} while ((point.getStatus() != Point.PointStatus.BLANK));
		
		point.OccupyWithFood();
	}

}
