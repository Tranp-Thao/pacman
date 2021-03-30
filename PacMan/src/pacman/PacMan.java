package pacman;

/**
 * Each instance of this class represents the player-controlled Pac-Man character in a Pac-Man maze.
 * @invar | getSquare() != null 
 * @invar | getNbLives() >= 0 
 * 
 * @mutable 
 */
public class PacMan {
	/**
	 * @invar | square != null  
	 * @representationObject     
	 */
	private Square square;
	/**
	 * @invar | nbLives >= 0
	 */
	private int nbLives;
	
	/**
	 * Returns the square position of this Pac-Man.
	 * @basic 
	 * @inspects | this 
	 * @creates | result //remove
	 */
	public Square getSquare() { 
		return this.square;
	}
	
	/**
	 * Returns number of lives of this Pac-Man.
	 * @basic
	 * @inspects | this
	 * @creates | result//remove
	 */
	public int getNbLives() { 
		return this.nbLives;
	}

	/**
	 * Initializes this Pac-Man with a given square position and a given number of lives.
	 * @throws IllegalArgumentException	| 0 > nbLives 
	 * @throws IllegalArgumentException	| square == null
	 * 
	 * @post | getNbLives() == nbLives
	 * @post | getSquare() == square      //getSquare().equals(square)  
	 */
	public PacMan(int nbLives, Square square) {
		if (nbLives < 0)
			throw new IllegalArgumentException("`nbLives` is less than 0.");
		if(square == null)
			throw new IllegalArgumentException("`square` is null.");
		
		this.nbLives = nbLives;
		this.square = square;
	}
	
	/**
	 * Sets the square location of this Pac-Man to the given square.
	 * @mutates | this 
	 * @throws IllegalArgumentExeption	| square == null 
	 * @post The new square position of this object is equal to the given square.
	 * 	| getSquare() == square && old(getSquare()).getMazeMap() == getSquare().getMazeMap()
	 */
	public void setSquare(Square square) {
		if(square == null)
			throw new IllegalArgumentException("`square` is null.");
		this.square = square;
	}
	
	/**
	 * Decreases this Pac-Man character's number of lives by one.
	 * @mutates | this
	 * @post | getNbLives() == old(getNbLives()) - 1
	 * @post | getNbLives() >= 0 
	 * 
	 */
	public void die() { 
		if (nbLives >=1) 
			nbLives = nbLives - 1;
	}

}
