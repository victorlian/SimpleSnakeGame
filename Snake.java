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
	
	private int length;
	private List<Point> occupiedPoints = new ArrayList<Point>();
	private Direction directionOfTravel;
	
	
	public Snake(){
		length = 3;
		for(int i=0; i<3; i++){
			Point p = GridPanel.getInstance().getPointAt(GridPanel.rows/2, GridPanel.rows/2+i);
			p.Occupy();
			occupiedPoints.add(p);
		}
		directionOfTravel = Snake.Direction.East;
	}
	
	/**
	 * This method will move the snake forward. If not successful, game should finish.
	 */
	public boolean nextMove(){
		System.out.println("moved!");
		Point first = occupiedPoints.remove(0);
		first.release();
		Point last = occupiedPoints.get(occupiedPoints.size()-1);
		int row = last.getRow();
		int col = last.getCol();
		
		switch(directionOfTravel){
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
		occupiedPoints.add(nextPoint);
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
}
