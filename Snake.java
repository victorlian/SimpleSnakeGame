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
	 * This method will move the snake forward.
	 */
	public void nextMove(){
		System.out.println("moved!");
		Point first = occupiedPoints.remove(0);
		first.release();
		
	}
}
