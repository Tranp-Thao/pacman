package pacman;

/**
 * Each instance of this class represents a dot (a piece of food for Pac-Man) in a Pac-Man maze.
 * 
 * @invar | getSquare() != null 
 */
public class Dot {
	/**
	 * @invar | square != null   
	 * @representationObject    
	 */
	private Square square;

	/**
	 * Return the square representing the position of this dot.
	 * @basic 
	 * @inspects | this
	 * @create | result //remove
	 */
	public Square getSquare() {
		return this.square; 
	}

	/**
	 * Initialize this object so that it represents a dot with given square position. 
	 * @throws IllegalArgumentException | square == null
	 * 
	 * @post | getSquare() == square 
	 */
	public Dot(Square square) {  
		if (square == null)
			throw new IllegalArgumentException("`square` is null");
		this.square = square;
	}

}

