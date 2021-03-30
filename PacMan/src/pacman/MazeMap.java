package pacman;

import java.util.stream.IntStream;

/**
 * Each instance of this class represents a maze layout, specifying the width and height of the maze
 * and, for each position in the maze, whether it is passable or not.
 * 
 * @invar | getWidth() > 0
 * @invar | getHeight() > 0
 * @invar | IntStream.range(0,getHeight()*getWidth()).allMatch(i-> isPassable(i/getWidth(),i%getWidth())==false || isPassable(i/getWidth(),i%getWidth())==true)
 * 
 * @immutable
 */
public class MazeMap {
	
	/**
	 * @invar | width > 0
	 * @invar | height > 0
	 */
	private int width;
	private int height;
	/**
	 * @invar | passable != null && passable.length > 0 
	 * @representationObject
	 */
	private boolean[] passable;

	/**
	 * Returns the width (i.e. the number of columns) of this maze map.
	 * @basic
	 * @inspects | this
	 */
	public int getWidth() { 
		return this.width;
	}
	
	/**
	 * Returns the height (i.e. the number of rows) of this maze map.
	 * @basic
	 * @inspects | this
	 */
	public int getHeight() { 
		return this.height;
	}
	
	/**
	 * Returns whether the square in this maze at row index {@code row} and column index {@code column} is passable.
	 * The square in the top-left corner of the maze has row index 0 and column index 0.
	 * @inspects | this
	 * @throws IllegalArgumentException | rowIndex < 0 && rowIndex >= getHeight()
	 * @throws IllegalArgumentException | columnIndex < 0 && columnIndex >= getWidth()
	 *
	 */
	public boolean isPassable(int rowIndex, int columnIndex) {
		if(rowIndex < 0)
			throw new IllegalArgumentException("`rowIndex` is negative");
		if(rowIndex > getHeight() )
				throw new IllegalArgumentException("`rowIndex` is above the height of the maze");
		if(columnIndex < 0)
			throw new IllegalArgumentException("`columnIndex` is negative");
		if(columnIndex > getWidth())
			throw new IllegalArgumentException("`columnIndex` is above the width of the maze");
		
		return passable[rowIndex*width + columnIndex];
	}
	
	/**
	 * Initializes this object so that it represents a maze layout with the given width, height, and
	 * passable positions. The passable positions are given in row-major order (i.e. the first {@code width} elements
	 * of {@code passable} specify the passability of the maze positions in the first row of the maze). 
	 * 
	 * @inspects | passable 
	 * 
	 * @throws IllegalArgumentException | width < 1
	 * @throws IllegalArgumentException | height <1
	 * @throws IllegalArgumentException | passable == null
	 * @throws IllegalArgumentException | passable.length == width * height
	 * 
	 * @post | getWidth() == width
	 * @post | getHeight() == height
	 * @post | getWidth()*getHeight() == passable.length
	 * @post | IntStream.range(0,passable.length).allMatch(i-> passable[i] == isPassable(i/width,i%width))  
	 */
	public MazeMap(int width, int height, boolean[] passable) {
		if (width < 1)
			throw new IllegalArgumentException("`number of row` is less than 1");
		if (height < 1)
			throw new IllegalArgumentException("`number of column` is less than 1");
		if (passable == null)
			throw new IllegalArgumentException("`passable` is null");
		if (passable.length != width * height)
			throw new IllegalArgumentException("length of `passable` is wrong");
		
		this.width = width;
		this.height = height;
		this.passable = passable.clone();
	}
}