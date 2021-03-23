package pacman;

import java.util.Random;

/**
 * Each instance of this class represents a ghost in a Pac-Man maze.
 */
public class Ghost {
	private Square square; //add
	private Direction direction;//add
	
	public Square getSquare() { //throw new RuntimeException("Not yet implemented"); 
		return this.square;//add
	}
	
	/**
	 * Returns the direction in which this ghost will preferably move next.
	 */
	public Direction getDirection() { //throw new RuntimeException("Not yet implemented");
		return this.direction;//add
		}
	
	public Ghost(Square square, Direction direction) { //throw new RuntimeException("Not yet implemented");
		this.square = square;//add
		this.direction = direction;//add
	}
	
	public void setSquare(Square square) {// throw new RuntimeException("Not yet implemented"); 
		this.square = square; // add
	}
	
	public void setDirection(Direction direction) {// throw new RuntimeException("Not yet implemented"); 
		this.direction = direction;//add
	}
	
	private static int MOVE_FORWARD_PREFERENCE = 10;
	
	// No formal document required
	public Direction chooseNextMoveDirection(Random random) {
		int moveForwardPreference = getSquare().canMove(getDirection()) ? MOVE_FORWARD_PREFERENCE : 0;
		Direction[] passableDirections = getSquare().getPassableDirectionsExcept(getDirection().getOpposite());
		if (passableDirections.length == 0)
			return getDirection().getOpposite();
		int result = random.nextInt(moveForwardPreference + passableDirections.length); // Returns number between 0 - (moveForwardPreference + passableDirections.length)
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
