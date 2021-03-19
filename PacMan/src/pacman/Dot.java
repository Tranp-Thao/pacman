package pacman;

/**
 * Each instance of this class represents a dot (a piece of food for Pac-Man) in a Pac-Man maze.
 */
public class Dot {
	private Square square;
	public Square getSquare() {//throw new RuntimeException("Not yet implemented"); 
		return this.square; //add
	}
	
	public Dot(Square square) { //throw new RuntimeException("Not yet implemented"); 
		this.square = square;//add
	}

}
