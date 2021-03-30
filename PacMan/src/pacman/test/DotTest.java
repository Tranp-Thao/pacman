package pacman.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import pacman.Dot;
import pacman.Maze;
import pacman.MazeDescriptions;
import pacman.MazeMap;
import pacman.Square;

class DotTest {

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
		MazeMap map = maze.getMap();             //return map
		Square square = Square.of(map, 1, 1);
		Dot dot = new Dot(square);
		assertEquals(square, dot.getSquare());
		Dot[] dotSequence = maze.getDots();      //test representation exposure without leaking
		assertNotEquals(square, dotSequence[0].getSquare());  //dots[nbDots++] = new Dot(Square.of(map, row, column)
		assertTrue(dotSequence[0].getSquare().equals(square)); //add

		// This is the test for new maze map 
		boolean[] myPassable = new boolean[27*21];
		for (int i = 0; i< myPassable.length; i++)
			myPassable[i] = true;
		MazeMap myMap = new MazeMap(21,27,myPassable);
		Square mySquare = Square.of(myMap, 1, 1);
		Dot myDot = new Dot(mySquare);
		assertEquals(mySquare, myDot.getSquare());
	}

}
