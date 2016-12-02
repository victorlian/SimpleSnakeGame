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
	
	private List<Point> _occupiedPoints = new ArrayList<Point>();
	private Direction _directionOfTravel;
	private Direction _nextDirection;
	
	
	public Snake(){
		for(int i=0; i<3; i++){
			Point p = GridPanel.getInstance().getPointAt(GridPanel.rows/2, GridPanel.rows/2+i);
			p.Occupy();
			_occupiedPoints.add(p);
		}
		_directionOfTravel = Snake.Direction.East;
	}
	
	/**
	 * This method will move the snake forward. If not successful, game should finish.
	 */
	public boolean nextMove(){
		if (_nextDirection!=null){
			_directionOfTravel = _nextDirection;
			_nextDirection=null;
		}
		System.out.println("moved!");
		Point first = _occupiedPoints.remove(0);
		first.release();
		Point last = _occupiedPoints.get(_occupiedPoints.size()-1);
		int row = last.getRow();
		int col = last.getCol();
		
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
			return false;
		}
		
		if (isTouching()){
			return false;
		}
		
		
		Point nextPoint = GridPanel.getInstance().getPointAt(row, col);
		nextPoint.Occupy();
		_occupiedPoints.add(nextPoint);
		return true;
		
	}
	
	/**
	 * This method returns if the point about to be added is already a snake point.
	 * 
	 * @return
	 */
	public boolean isTouching(){
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
	
}
