package pacman;

import java.util.Random;

/**
 * Each instance of this class represents a ghost in a Pac-Man maze.
 * 
 * @invar | getSquare() != null 
 * @invar | getDirection() != null  
 * 
 * @mutable
 */
public class Ghost {
	/**
	 * @invar | square != null  
	 * @invar | direction != null 
	 * @representationObjects
	 */
	private Square square; 
	private Direction direction;
	
	/**
	 * Returns the square position of this ghost.
	 * @basic
	 * @inspects | this
	 * @creates | result  //remove
	 */
	public Square getSquare() {
		return this.square;
	}
	
	/**
	 * Returns the direction in which this ghost will preferably move next.
	 * @basic
	 * @inspects | this
	 * @creates | result   //remove
	 */
	public Direction getDirection() { 
		return this.direction;
		}
	
	/**
	 * Initializes this ghost with a given square position and an initial moving direction.
	 * @throws IllegalArgumentException | square == null
	 * @throws IllegalArgumentException | direction == null
	 * 
	 * @post | getSquare() == square
	 * @post | direction.equals(getDirection()) //getDirection() == direction
	 */
	public Ghost(Square square, Direction direction) { 
		if (square == null)
			throw new IllegalArgumentException("`square` is null");
		if (direction == null)
			throw new IllegalArgumentException("`direction` is null");
		this.square = square;
		this.direction = direction;
	}
	
	/**
	 * Set the square representing this ghost position to the given square.
	 * @mutates | this 
	 * 
	 * @throws IllegalArgumentException | square == null
	 * @post The new square position of this ghost is equal to the given square.
	 * 	| getSquare() == square && old(getSquare()).getMazeMap() == getSquare().getMazeMap()
	 */
	public void setSquare(Square square) {
		if (square == null)
			throw new IllegalArgumentException("`square` is null");
		this.square = square; 
	}
	
	/**
	 * Set the new direction preferable moving direction of this ghost to the given direction. 
	 * @mutates | this 
	 * 
	 * @throws IllegalArgumentException | direction == null
	 * @post The new preferable moving direction of this ghost is equal to the given direction.
	 * 	| getDirection() == direction 
	 */
	public void setDirection(Direction direction) {
		if (direction == null)
			throw new IllegalArgumentException("`direction` is null");
		this.direction = direction;
	}
	
	private static int MOVE_FORWARD_PREFERENCE = 10;
	
	// No formal document required
	public Direction chooseNextMoveDirection(Random random) {
		int moveForwardPreference = getSquare().canMove(getDirection()) ? MOVE_FORWARD_PREFERENCE : 0;
		Direction[] passableDirections = getSquare().getPassableDirectionsExcept(getDirection().getOpposite());
		if (passableDirections.length == 0)
			return getDirection().getOpposite();
		int result = random.nextInt(moveForwardPreference + passableDirections.length);
		if (result < moveForwardPreference)
			return getDirection();
		return passableDirections[result - moveForwardPreference];
	}
	
	// No formal document required
	public void move(Random random) {
		setDirection(chooseNextMoveDirection(random));
		setSquare(getSquare().getNeighbor(getDirection()));
	}
}
