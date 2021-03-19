package pacman;

import java.util.Arrays;

/**
 * Each instance of this class represents a position in a maze, specified by a row index and a column index.
 * The top row and the leftmost column have index 0.
 */
public class Square {
	private int rowIndex;//add
	private int columnIndex;//add
	private MazeMap map;//add
	
	private Square() {throw new RuntimeException("Not yet implemented");}//add
	
	public MazeMap getMazeMap() {// throw new RuntimeException("Not yet implemented");
		return this.map;//add
	}
	
	public int getRowIndex() { //throw new RuntimeException("Not yet implemented"); 
		return this.rowIndex;//add
	}
	
	public int getColumnIndex() { //throw new RuntimeException("Not yet implemented"); 
		return this.columnIndex;//add
	}
	
	public boolean isPassable() {//throw new RuntimeException("Not yet implemented"); 
		
		return map.isPassable(rowIndex, columnIndex);
	}
	
	public static Square of(MazeMap mazeMap, int rowIndex, int columnIndex) {
		return Square.of(mazeMap,rowIndex,columnIndex);//add
		//throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * Returns this square's neighbor in the given direction.
	 * If this square has no neighbor in the given direction, return the square that is furthest away in the opposite direction.
	 */
	// No formal documentation required
	public Square getNeighbor(Direction direction) {
		return null;
		// Implementation hint: use method java.lang.Math.floorMod.
		//throw new RuntimeException("Not yet implemented");
	}

	/**
	 * Returns whether this square's neighbor in the given direction is passable.
	 */
	// No formal documentation required
	public boolean canMove(Direction direction) {
		return false;
		//throw new RuntimeException("Not yet implemented");
	}

	/**
	 * Returns the directions that are different from the given excluded direction and such that the neighbor in that direction is passable.
	 * The returned array shall have no null elements and shall have no duplicates.
	 */
	// No formal documentation required
	public Direction[] getPassableDirectionsExcept(Direction excludedDirection) {
		
		return null;
		//throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * Returns whether the given square refers to the same {@code MazeMap} object and has the same row and column index as this square.  
	 */
	public boolean equals(Square other) {
		if (other.getMazeMap()==map && other.getRowIndex()==rowIndex && other.getColumnIndex()==colIndex)//add
			return true; //add
		return false; //add
		//throw new RuntimeException("Not yet implemented");
	}
	
}
