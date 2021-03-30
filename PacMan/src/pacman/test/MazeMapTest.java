package pacman.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import pacman.Maze;
import pacman.MazeDescriptions;
import pacman.MazeMap;


class MazeMapTest {

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
		assertEquals(27, map.getHeight());
		assertEquals(21, map.getWidth());
		assertTrue(map.isPassable(1, 1));
		assertFalse(map.isPassable(0, 10));


		// This is the test for new maze map 
		boolean[] myPassable = new boolean[27*21];
		for (int i = 0; i< 27; i++)
			myPassable[i] = true;

		for (int i = 0; i< myPassable.length; i++)
			System.out.println(myPassable[i]);
		MazeMap myMap = new MazeMap(21,27, myPassable);
		assertEquals(21, myMap.getWidth());
		assertEquals(27, myMap.getHeight());
		assertEquals(true, myMap.isPassable(1, 1));
		assertEquals(false, myMap.isPassable(10, 5));
	}

}
