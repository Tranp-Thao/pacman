package pacman;

/**
 * Each instance of this class represents the player-controlled Pac-Man character in a Pac-Man maze.
 */
public class PacMan {
	private Square square;//add
	private int nbLives;//add
	
	public Square getSquare() { //throw new RuntimeException("Not yet implemented"); 
		return this.square;//add
	}
	
	public int getNbLives() { //throw new RuntimeException("Not yet implemented");
		return this.nbLives;
	}

	public PacMan(int nbLives, Square square) {//throw new RuntimeException("Not yet implemented");
		this.nbLives = nbLives;//add
		this.square = square;//add
	}
	
	public void setSquare(Square square) { //throw new RuntimeException("Not yet implemented"); 
		this.square = square;//add
	}
	
	/**
	 * Decreases this Pac-Man character's number of lives by one.
	 */
	public void die() { //throw new RuntimeException("Not yet implemented"); 
		if (nbLives >=1) //add
			nbLives = nbLives - 1; //add
	}

}
