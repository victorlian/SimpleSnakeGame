import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * This class represents the 2D grid that is to be drawn on the frame.
 * @author Victor
 *
 */
public class GridPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public static final int rows = 30;
	public static final int cols = 30;
	private static final int rowDim = Frame.sizeGridRow;
	private static final int colDim = Frame.sizeGridCol;
	
	private Point[][] points= new Point[rows][cols];

	private static GridPanel instance;
	
	public static GridPanel getInstance(){
		if (instance==null){
			instance=new GridPanel();
		}
		return instance;
	}
	
	private GridPanel(){
		this.setPreferredSize(new Dimension(rowDim, colDim));
		LayoutManager grid = new GridLayout(rows,cols);
		this.setLayout(grid);
		initPoint();		
	}
	
	/**
	 * This is a helper method that will initialize the points array
	 * and adds it to the panel
	 */
	public void initPoint(){
		for (int i=0; i<rows; i++){
			for (int j=0; j<cols; j++){
				Point p = new Point(i, j);
				points[i][j]=p;
				this.add(p);
			}
		}
	}
	
	/**
	 * This method returns the point that's in the grid given
	 * the co-ordinate
	 */
	public Point getPointAt(int row, int col){
		return points[row][col];
	}
	
	/**
	 * This method returns the point that's
	 */
	

}
