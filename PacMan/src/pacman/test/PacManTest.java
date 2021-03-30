package pacman.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import pacman.Maze;
import pacman.MazeDescriptions;
import pacman.MazeMap;
import pacman.PacMan;
import pacman.Square;

class PacManTest {

	@Test
	void test() {
		// This is the test for current used maze map
		Maze maze = MazeDescriptions.createMazeFromDescription(new Random(), """
				#####################
				#.........#.........#
				#.###.###.#.###.###.#
				#.###.###.#.###.###.#
				#.###.###.#.###.###.#
				#...................#
				#.###.#.#####.#.###.#
				#.###.#.#####.#.###.#
				#.....#...#...#.....#
				#####.### # ###.#####
				    #.#       #.#    
				    #.# # G # #.#    
				#####.# #   # #.#####  
				     .  #GGG#  .        
				#####.# ##### #.#####
				    #.#       #.#    
				    # # ##### #.#    
				#####.# ##### #.#####
				#.........#.........#
				#.###.###.#.###.###.#
				#...#.....P.....#...#
				###.#.#.#####.#.#.###
				###.#.#.#####.#.#.###
				#.....#...#...#.....#
				#.#######.#.#######.#
				#...................#
				#####################
				""");
		MazeMap map = maze.getMap();
		//Pacman position (row = 10, column = 20)
		PacMan pacman = maze.getPacMan();
		Square square = Square.of(map, 20, 10);
		assertTrue(pacman.getSquare().equals(square));
		assertEquals(3, pacman.getNbLives());
		
		PacMan new_pacman = new PacMan(1, square);
		assertEquals(square, new_pacman.getSquare());
		assertEquals(1, new_pacman.getNbLives());

		Square new_square = Square.of(map, 20, 11);
		new_pacman.setSquare(new_square);
		assertEquals(new_square, new_pacman.getSquare());
		
		new_pacman.die();
		assertEquals(0, new_pacman.getNbLives());


		// This is the test for new maze map 
		boolean[] myPassable = new boolean[27*21];
		for (int i = 0; i< myPassable.length; i++)
			myPassable[i] = true;

		MazeMap myMap = new MazeMap(21,27,myPassable);
		Square mySquare = Square.of(myMap, 1, 1);
		Square otherSquare = Square.of(myMap, 1, 2);
		pacman.PacMan myPacman = new PacMan(2, mySquare);

		assertEquals(mySquare, myPacman.getSquare());
		assertEquals(2, myPacman.getNbLives());

		myPacman.setSquare(otherSquare);
		assertEquals(otherSquare, myPacman.getSquare());

		myPacman.die();
		assertEquals(1, myPacman.getNbLives());

	}

}
