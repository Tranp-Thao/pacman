package pacman;

import java.util.Arrays;

/**
 * Each instance of this class represents a position in a maze, specified by a row index and a column index.
 * The top row and the leftmost column have index 0.
 * 
 * 
 */
@SuppressWarnings("unused")
public class Square {
	private int rowIndex;//add
	private int columnIndex;//add
	private MazeMap map;//add
	
	private Square(MazeMap mazeMap,int rowIndex,int columnIndex) {//add
		this.map = mazeMap;//add
		this.rowIndex = rowIndex;//add
		this.columnIndex = columnIndex;//add
	}//add
	
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
		
		return this.map.isPassable(this.rowIndex, this.columnIndex);//add
	}
	
	public static Square of(MazeMap mazeMap, int rowIndex, int columnIndex) {
		return new Square(mazeMap,rowIndex,columnIndex);//add???
		//throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * Returns this square's neighbor in the given direction.
	 * If this square has no neighbor in the given direction, return the square that is furthest away in the opposite direction.
	 */
	// No formal documentation required
	public Square getNeighbor(Direction direction) {
		int neighborRow;//add
		int neighborCol;//add
		Square neighbor = null;//add
		switch (direction) {//add
		case RIGHT: neighborRow = this.rowIndex ;//add
		neighborCol = this.columnIndex + 1 ;//add
		if (neighborCol <= this.map.getWidth()-2)//add
			neighbor = Square.of(this.map, neighborRow, neighborCol);//add
		else
			neighbor = Square.of(this.map, neighborRow, Math.floorMod(neighborCol,this.map.getWidth()*(-1)));
		break;//add	
		case DOWN: neighborRow = this.rowIndex + 1 ;//add
		neighborCol = this.columnIndex;//add
		if (neighborRow <= this.map.getHeight()-2)//add
			neighbor = Square.of(this.map, neighborRow, neighborCol);//add
		break;//add
		case LEFT: neighborRow = this.rowIndex ;//add
		neighborCol = this.columnIndex - 1 ;//add
		if (neighborCol <= this.map.getWidth()-2 && neighborCol>=1)//add
			neighbor = Square.of(this.map, neighborRow, neighborCol);//add
		else 
			neighbor = Square.of(this.map, neighborRow, Math.floorMod(neighborCol,this.map.getWidth()));
		break;//add
		case UP: neighborRow = this.rowIndex - 1 ;//add
		neighborCol = this.columnIndex;//add
		if (neighborRow <= this.map.getHeight()-2 && neighborRow>=1)//add
			neighbor = Square.of(this.map, neighborRow, neighborCol);//add
		break;//add


		}//add
		return neighbor;//add
		
		
		// Implementation hint: use method java.lang.Math.floorMod.
		//throw new RuntimeException("Not yet implemented");
	}

	/**
	 * Returns whether this square's neighbor in the given direction is passable.
	 */
	// No formal documentation required
	public boolean canMove(Direction direction) {
		if(getNeighbor(direction) != null)//add   // this one to prevent the ghost go over the map line
			return getNeighbor(direction).isPassable(); //add
		return false;//add
		//throw new RuntimeException("Not yet implemented");
	}

	/**
	 * Returns the directions that are different from the given excluded direction and such that the neighbor in that direction is passable.
	 * The returned array shall have no null elements and shall have no duplicates.
	 */
	// No formal documentation required
	public Direction[] getPassableDirectionsExcept(Direction excludedDirection) {
		Direction direct = excludedDirection;
		System.out.print(direct.toString());
		
		int index = 0;
		switch (excludedDirection) {
		case RIGHT: index = 0;
		case DOWN: index = 1;
		case LEFT: index = 2;
		case UP: index = 3;
		}
		System.out.print("" + index);
		// create an array to hold elements after deletion
		Direction[] passableDirections = new Direction[Direction.values().length-1];
		// copy elements from original array from beginning till index into copyArray
		System.arraycopy(Direction.values(), 0, passableDirections, 0, index);
		// copy elements from original array from index+1 till end into copyArray
		System.arraycopy(Direction.values(), index + 1, passableDirections, index, Direction.values().length - index - 1);
	   System.out.print(""+ passableDirections.length + passableDirections[0] + passableDirections[1] + passableDirections[2]);
	   for (int i = 0; i < passableDirections.length ; i++) {   //length = 3
	   if (!canMove(passableDirections[i])) { 
		   index = i;
		   Direction[] newPassableDirections = new Direction[passableDirections.length -1];
		   System.arraycopy(passableDirections, 0, newPassableDirections, 0, index);
		   System.arraycopy(passableDirections, index + 1, newPassableDirections, index, passableDirections.length - index - 1);
		   passableDirections = newPassableDirections;
	   }
	   }
	   
//		Direction[] passableDirections = new Direction[Direction.values().length-1];//add
//		
//		int index=0;//add
//		Direction[] passableDirections = Direction.values().clone();
//		switch (excludedDirection) {//add
//			case RIGHT: index = 0;//add
//			for(int i=0;i<=3;i++)
//				if(i!=index)
//					if(!canMove(Direction.values()[i]))
//						System.arraycopy(Direction.values(), 0, passableDirections, 0, i);
//	
//			System.arraycopy(Direction.values(), index + 1, passableDirections, index, Direction.values().length-1);//add
//			case DOWN: index = 1;//add
//			System.arraycopy(Direction.values(), index+ 1, passableDirections, index, Direction.values().length-1);//add
//			case LEFT: index = 2;//add
//			System.arraycopy(Direction.values(), index+ 1, passableDirections, index, Direction.values().length-1);//add
//			case UP: index = 3;//add
//			System.arraycopy(Direction.values(), index+ 1, passableDirections, index, Direction.values().length-1);//add
//		}
		return passableDirections;//add
		//throw new RuntimeException("Not yet implemented"); 
	}
	
	
	
	
  
	
	/**
	 * Returns whether the given square refers to the same {@code MazeMap} object and has the same row and column index as this square.  
	 */
	public boolean equals(Square other) {
		if (other.getMazeMap()==map && other.getRowIndex()==rowIndex && other.getColumnIndex()==columnIndex)//add
			return true; //add
		return false; //add
		//throw new RuntimeException("Not yet implemented");
	}
/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + columnIndex;
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		result = prime * result + rowIndex;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Square other = (Square) obj;
		if (columnIndex != other.columnIndex)
			return false;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		if (rowIndex != other.rowIndex)
			return false;
		return true;
	}
	*/ // does equals need override???
}
