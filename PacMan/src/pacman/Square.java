package pacman;

import java.util.ArrayList;//add
import java.util.Arrays;
import java.util.Collection;//add
import java.util.EnumMap;//add
import java.util.List;//add
import java.util.Objects;//add

/**
 * Each instance of this class represents a position in a maze, specified by a
 * row index and a column index. The top row and the leftmost column have index
 * 0.
 * 
 * @invar | getRowIndex() >= 0 && getRowIndex() < getMazeMap().getHeight()
 * @invar | getColumnIndex() >= 0 && getColumnIndex() < getMazeMap().getWidth()
 * @invar | getMazeMap() != null
 * @immutable
 */
@SuppressWarnings("unused")
public class Square {
	/**
	 * @invar | rowIndex >= 0 && rowIndex < map.getHeight()
	 * @invar | columnIndex >= 0 && columnIndex < map.getWidth()
	 */
	private int rowIndex;
	private int columnIndex;
	/**
	 * @invar | map != null
	 * @representationObject
	 */
	private MazeMap map;
	
	/**
	 * Initializes this square object within a given maze with a given row index and column index.
	 *
	 */
	private Square(MazeMap mazeMap, int rowIndex, int columnIndex) {
		this.map = mazeMap;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}
	
	/**
	 * Return the the maze that this square belongs to.
	 * @basic
	 * @inspects | this
	 * @creates | result //remove
	 */
	public MazeMap getMazeMap() {
		return this.map;
	}

	/**
	 * Return the row index of this square.
	 * @basic
	 */
	public int getRowIndex() { 
		return this.rowIndex;
	}

	/**
	 * Return the column index of this square.
	 * @basic
	 */
	public int getColumnIndex() { 
		return this.columnIndex;
	}

	/**
	 * Check whether this square is passable or not.
	 * @inspects | this.getMazeMap()
	 * @return True if and only if the position at the same row index and column index 
	 * of the maze map that this square belongs to is also passable.
	 * @post | result == getMazeMap().isPassable(getRowIndex(),getColumnIndex())
	 */
	public boolean isPassable() {

		return this.map.isPassable(this.rowIndex, this.columnIndex);
	}

	/**
	 * Return a new square object within the given maze and the given row index and column index.
	 * @creates | result
	 * @throws IllegalArgumentException | mazeMap ==  null 
	 * @throws IllegalArgumentException | 0 > rowIndex 
	 * @throws IllegalArgumentException | rowIndex >= mazeMap.getHeight()
	 * @throws IllegalArgumentException | 0 > columnIndex
	 * @throws IllegalArgumentException | columnIndex >= mazeMap.getWidth()
	 * 
	 * @post | result != null
	 * @post | result.getMazeMap().equals(mazeMap)
	 * @post | result.getRowIndex() == rowIndex
	 * @post | result.getColumnIndex() == columnIndex
	 */
	public static Square of(MazeMap mazeMap, int rowIndex, int columnIndex) { 
		if (mazeMap ==  null)
			throw new IllegalArgumentException("`mazeMap` is null");
		if (rowIndex < 0)
			throw new IllegalArgumentException("`rowIndex` is negative");
		if (rowIndex >= mazeMap.getHeight())
			throw new IllegalArgumentException("`rowIndex` is larger than the heigth of `mazeMap`");
		if (columnIndex < 0)
			throw new IllegalArgumentException("`columIndex` is negative");
		if (columnIndex >= mazeMap.getWidth())
			throw new IllegalArgumentException("`columnIndex` is larger than the width of `mazeMap`");
		
		return new Square(mazeMap, rowIndex, columnIndex);
	}

	/**
	 * Returns this square's neighbor in the given direction. If this square has no
	 * neighbor in the given direction, return the square that is furthest away in
	 * the opposite direction.
	 */
	// No formal documentation required
	public Square getNeighbor(Direction direction) {
		int neighborRow;
		int neighborCol;
		Square neighbor = new Square(map, 0, 0);

		switch (direction) {
		case RIGHT:
			neighborRow = this.rowIndex;
			neighborCol = this.columnIndex + 1;
			if (neighborCol <= this.map.getWidth() - 2)
				neighbor = Square.of(this.map, neighborRow, neighborCol);
			else
				neighbor = Square.of(this.map, neighborRow, Math.floorMod(neighborCol, this.map.getWidth()));
			break;
		case DOWN:
			neighborRow = this.rowIndex + 1;
			neighborCol = this.columnIndex;
			if (neighborRow <= this.map.getHeight() - 2)
				neighbor = Square.of(this.map, neighborRow, neighborCol);
			else
				neighbor = Square.of(this.map, Math.floorMod(neighborRow, this.map.getHeight()), neighborCol);//
			break;
		case LEFT:
			neighborRow = this.rowIndex;
			neighborCol = this.columnIndex - 1;
			if (neighborCol <= this.map.getWidth() - 2 && neighborCol >= 1)
				neighbor = Square.of(this.map, neighborRow, neighborCol);
			else
				neighbor = Square.of(this.map, neighborRow, Math.floorMod(neighborCol, this.map.getWidth()));
			break;
		case UP:
			neighborRow = this.rowIndex - 1;
			neighborCol = this.columnIndex;
			if (neighborRow <= this.map.getHeight() - 2 && neighborRow >= 1)
				neighbor = Square.of(this.map, neighborRow, neighborCol);
			else
				neighbor = Square.of(this.map, Math.floorMod(neighborRow, this.map.getHeight()), neighborCol);//
			break;
		}
		return neighbor;
	}

	/**
	 * Returns whether this square's neighbor in the given direction is passable.
	 */
	// No formal documentation required
	public boolean canMove(Direction direction) {
		Square neighbor = getNeighbor(direction);
		if (neighbor != null)
			return neighbor.isPassable(); 
		return false;
	}

	/**
	 * Returns the directions that are different from the given excluded direction
	 * and such that the neighbor in that direction is passable. The returned array
	 * shall have no null elements and shall have no duplicates.
	 */
	// No formal documentation required
	public Direction[] getPassableDirectionsExcept(Direction excludedDirection) {
		List<Direction> newDirections = new ArrayList<>();
		List<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));
		
		int index = -1;
		for (int i = 0; i < directions.size(); i++) {
			if (directions.get(i).name().equals(excludedDirection.name())) {
				continue;
			} else {
				newDirections.add(directions.get(i));
			}
		}
		
		Direction[] pre_passableDirections = new Direction[newDirections.size()];
		for (int i = 0; i < newDirections.size(); i++) { 
			if (canMove(newDirections.get(i))) {
				pre_passableDirections[i] = newDirections.get(i);
			}
		}
		Direction[] passableDirections = Arrays.stream(pre_passableDirections).filter(Objects::nonNull).toArray(Direction[]::new);
		return passableDirections.clone();
	}

	/**
	 * Returns whether the given square refers to the same {@code MazeMap} object
	 * and has the same row and column index as this square.
	 * @throws IllegalArgumentException | other == null
	 * 
	 */
	public boolean equals(Square other) {  
		if(other == null)
			throw new IllegalArgumentException("`other` is null");
		if (other.getMazeMap().equals(map) && other.getRowIndex() == rowIndex && other.getColumnIndex() == columnIndex)
			return true; 
		return false; 
	}
}

