import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a snake instance.
 * A snake has: length, occupiedPoints, directionOfTravel
 * @author Victor
 *
 */
public class Snake {
	public enum Direction{
		North, South, East, West;
	}
	public enum GameStatus{
		WON, CONTINUE, LOST;
	}
	
	private List<Point> _occupiedPoints = new ArrayList<Point>(); //Warning, this should be a stack operation.
	private Direction _directionOfTravel;
	private Direction _nextDirection;
	
	
	public Snake(){
		for(int i=0; i<3; i++){

			Point p = GridPanel.getInstance().getPointAt(GridPanel.rows/2, GridPanel.rows/2+i);
			p.Occupy();
			_occupiedPoints.add(p);
			
//			/*
//			 * For debug use:
//			 */
//			if (i==0){
//				p.colourHead();
//				break;
//			}
			//NORMAL
			if (i==2){
				p.colourHead(); // colour the head to red.
			}

		}
		_directionOfTravel = Snake.Direction.East;
	}
	
	/**
	 * This method will move the snake forward. If not successful, game should finish.
	 */
	public GameStatus nextMove(){
		if (_nextDirection!=null){
			_directionOfTravel = _nextDirection;
			_nextDirection=null;
		}
		Point first = _occupiedPoints.get(0);
		Point last = _occupiedPoints.get(_occupiedPoints.size()-1);
		int row = last.getRow();
		int col = last.getCol();
		last.Occupy(); // this should change the colour back to red.
		
		switch(_directionOfTravel){
			case North:
				row--;
				break;
			case South:
				row++;
				break;
			case West:
				col--;
				break;
			case East:
				col++;
				break;
		}
		
		if (!GridPanel.getInstance().isInBound(row, col)){
			return GameStatus.LOST;
		}
		

		Point nextPoint = GridPanel.getInstance().getPointAt(row, col);
		
		if (isTouching(nextPoint)){
			return GameStatus.LOST;
		}
		
		//As soon as the snake eats the food, it should generate the next food position.
		if (isEating(nextPoint) && isFull()){
			nextPoint.Occupy();
			nextPoint.colourHead();
			return GameStatus.WON;
		}
		
		if (!isEating(nextPoint)){
			first.release();
			_occupiedPoints.remove(0);
		} else {
			Food.getInstance().updateFood();
		}

		nextPoint.Occupy();
		nextPoint.colourHead();
		_occupiedPoints.add(nextPoint);
		return GameStatus.CONTINUE;
		
	}
	
	/**
	 * This method returns if the point about to be added is already a snake point.
	 * 
	 * @return
	 */
	public boolean isTouching(Point nextPoint){
		if (nextPoint.getStatus() == Point.PointStatus.SNAKE){
			return true;
		}
		return false;
	}
	
	/**
	 * This method returns if the point about to be added is a food point.

	 * 
	 * @return
	 */
	public boolean isEating(Point nextPoint){
		if (nextPoint.getStatus() == Point.PointStatus.FOOD){
			return true;
		}
		return false;
	}
	
	/**
	 * Methods for changing direction purpose.
	 */
	public void toNorth(){
		if (_directionOfTravel != Snake.Direction.South && _nextDirection ==null){
			_nextDirection = Snake.Direction.North;
		}
		
	}
	public void toSouth(){
		if (_directionOfTravel != Snake.Direction.North && _nextDirection == null){
			_nextDirection = Snake.Direction.South;
		}
	}
	public void toEast(){
		if (_directionOfTravel != Snake.Direction.West && _nextDirection == null){
			_nextDirection = Snake.Direction.East;
		}
	}
	
	public void toWest(){
		if (_directionOfTravel != Snake.Direction.East && _nextDirection == null){
			_nextDirection = Snake.Direction.West;
		}
	}
	
	/**
	 * Method for checking if the snake occupies the entire screen.
	 */
	public boolean isFull(){
		return ((_occupiedPoints.size()+1)==GridPanel.cols * GridPanel.rows);
	}
	
}
