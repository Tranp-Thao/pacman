package pacman.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.Ghost;
import pacman.Maze;
import pacman.MazeDescriptions;
import pacman.MazeMap;
import pacman.Square;

class GhostTest {

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
		Square square = Square.of(map, 11, 10);
		//Test on ghost G(row = 11, column = 10) 
		Ghost first_ghost = maze.getGhosts()[0];
		assertTrue(first_ghost.getSquare().equals(square));
		
		Ghost ghost = new Ghost(square,Direction.LEFT);
		assertEquals(square, ghost.getSquare());
		assertEquals(Direction.LEFT,ghost.getDirection());
		
		ghost.setDirection(Direction.RIGHT);
		assertEquals(Direction.RIGHT,ghost.getDirection());
		Square new_square = Square.of(map, 12, 13);
		ghost.setSquare(new_square);
		assertEquals(new_square,ghost.getSquare());
		
		
		// This is the test for new maze map 
		boolean[] myPassable = new boolean[27*21];
		for (int i = 0; i< myPassable.length; i++)
			myPassable[i] = true;
		
		MazeMap myMap = new MazeMap(21,27,myPassable);
		Square mySquare = Square.of(myMap, 1, 1);
		Direction myDirection = Direction.RIGHT;
		Ghost myGhost = new Ghost(mySquare, myDirection);
		
		assertEquals(mySquare, myGhost.getSquare());
		assertEquals(myDirection, myGhost.getDirection());
		
		Square otherSquare = Square.of(myMap, 1, 2);
		myGhost.setSquare(otherSquare);
		assertEquals(otherSquare, myGhost.getSquare());
		
		Direction otherDirection = Direction.DOWN;
		myGhost.setDirection(otherDirection);
		assertEquals(otherDirection, myGhost.getDirection());
		
	}

}
