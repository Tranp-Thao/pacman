package pacman;

/**
 * Each instance of this class represents a maze layout, specifying the width and height of the maze
 * and, for each position in the maze, whether it is passable or not.
 */
public class MazeMap {
	private int width;//add
	private int height;//add
	private boolean[] passable;//add

	/**
	 * Returns the width (i.e. the number of columns) of this maze map.
	 */
	public int getWidth() { //throw new RuntimeException("Not yet implemented"); 
		return this.width;//
	}
	
	/**
	 * Returns the height (i.e. the number of rows) of this maze map.
	 */
	public int getHeight() { //throw new RuntimeException("Not yet implemented"); 
		return this.height;
	}
	
	/**
	 * Returns whether the square in this maze at row index {@code row} and column index {@code column} is passable.
	 * The square in the top-left corner of the maze has row index 0 and column index 0.
	 */
	public boolean isPassable(int rowIndex, int columnIndex) {// throw new RuntimeException("Not yet implemented"); 
		return passable[rowIndex*width + columnIndex];//add
	}
	
	/**
	 * Initializes this object so that it represents a maze layout with the given width, height, and
	 * passable positions. The passable positions are given in row-major order (i.e. the first {@code width} elements
	 * of {@code passable} specify the passability of the maze positions in the first row of the maze). 
	 */
	public MazeMap(int width, int height, boolean[] passable) {
		//throw new RuntimeException("Not yet implemented");
		if (width < 1)//add
			throw new IllegalArgumentException("`number of row` is less than 1");//add
		if (height < 1)//add
			throw new IllegalArgumentException("`number of column` is less than 1");//add
		if (passable == null)//add
			throw new IllegalArgumentException("`passable` is null");//add
		if (passable.length != width * height)//add
			throw new IllegalArgumentException("length of `passable` is wrong");//add
		
		this.width = width;//add
		this.height = height;//add
		this.passable = passable.clone();//add
	}
}
