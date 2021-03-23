package pacman;

/**
 * Each instance of this class represents a dot (a piece of food for Pac-Man) in a Pac-Man maze.
 * 
 * @invar | getSquare() != null  //add
 */
public class Dot {
	/**
	 * @invar | square != null   // add
	 * @representationObject     //add
	 */
	private Square square; //add
	/**
	 * @Basic //?
	 * Return the square representing the position of this object.  //add
	 * @create | result  //??need or not 
	 */
	public Square getSquare() {//throw new RuntimeException("Not yet implemented"); //inspector,can not be defined by other method => basic
		return this.square; //add
	}
	/**
	 * Initialize this object so that it represents a dot with given square position. //add
	 * @pre square != null //add  //nominal way
	 * 
	 * @post | getSquare() == square //add 
	 */
	public Dot(Square square) { //throw new RuntimeException("Not yet implemented"); 
		this.square = square;//add
	}

}
