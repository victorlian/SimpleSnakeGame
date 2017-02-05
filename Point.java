import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


/**
 * This class represent a point on a grid.
 * A point is either blank, or occupied by snake or occupied by food.
 * Each point is actually also a JPanel, allowing it to colour itself accordingly.
 * @author Victor
 *
 */
public class Point extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public enum PointStatus {
		BLANK, SNAKE, FOOD
	}
	
	private static final Color bg = Color.GRAY;
	private static final Color snakeColour = Color.RED;
	private static final Color foodColour = Color.GREEN;
	private static final Color headColour = Color.ORANGE;
	private PointStatus _ps = PointStatus.BLANK;
	private final int _row;
	private final int _col;
	
	public Point(int row, int col){
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		changeColour();
		_row = row;
		_col = col;
	}
	
	/**
	 * This method will occupy the current point with snake.
	 */
	public void Occupy(){
		_ps = PointStatus.SNAKE;
		changeColour();
	}
	
	/**
	 * This method will occupy the current point with food.
	 */
	public void OccupyWithFood(){
		_ps = PointStatus.FOOD;
		changeColour();
	}
	
	/**
	 * This method will release the current point.
	 */
	public void release(){
		_ps = PointStatus.BLANK;
		changeColour();
	}
	
	/**
	 * This method will change the colour of the current point in accordance to 
	 * the point status.
	 */
	private void changeColour() {
		switch(_ps){
			case BLANK: 
				this.setBackground(bg);
				break;
			case SNAKE:
				this.setBackground(snakeColour);
				break;
			case FOOD:
				this.setBackground(foodColour);
				break;
		}
	}
	
	/**
	 * This method will colour the head to a different colour!
	 * Only call this method on an occupied point.
	 */
	public void colourHead(){
		if (_ps != PointStatus.SNAKE){
			throw new RuntimeException("Cannot colour head!");
		}
		else {
			this.setBackground(headColour);
		}
	}
	/**
	 * This method will return the row index of a point.
	 */
	public int getRow(){
		return _row;
	}
	
	/**
	 * This method will return the col index of a point
	 */
	public int getCol(){
		return _col;
	}
	
	/**
	 * This method will return the point status. (whether the point is empty)
	 */
	public PointStatus getStatus(){
		return _ps;
	}

}
