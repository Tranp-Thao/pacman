package pacman.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.Maze;//add
import pacman.MazeDescriptions;//add
import pacman.MazeMap;//add
import pacman.Square;//add

class SquareTest {

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
		//Check square at position (row 1, column 2): passable (a dot), can move left/right, can not move up/down
		Square square1 = Square.of(map, 1, 2);
		assertEquals(map,square1.getMazeMap());
		assertEquals(1, square1.getRowIndex());
		assertEquals(2, square1.getColumnIndex());
		assertTrue(square1.isPassable());
		
		assertNotEquals(Square.of(map, 1, 3), square1.getNeighbor(Direction.RIGHT)); //check representation exposure leaking
		assertTrue(square1.getNeighbor(Direction.RIGHT).equals(Square.of(map, 1, 3)));
		
		assertFalse(square1.canMove(Direction.UP));
		assertFalse(square1.canMove(Direction.DOWN));
		assertTrue(square1.canMove(Direction.LEFT));
		assertTrue(square1.canMove(Direction.RIGHT));
		                                                                             //check possible moving direction
		assertArrayEquals(new Direction[] {Direction.RIGHT,Direction.LEFT}, square1.getPassableDirectionsExcept(Direction.DOWN));
		assertEquals(Direction.RIGHT, square1.getPassableDirectionsExcept(Direction.LEFT)[0]);
		assertEquals(Direction.LEFT, square1.getPassableDirectionsExcept(Direction.RIGHT)[0]);
		//Check square at position (row 1, column 0): not passable (wall)
		Square square2 = Square.of(map, 1, 0);
		assertFalse(square2.isPassable());
		//Check if two square objects are equal
		Square square3 = Square.of(map, 1, 2);
		assertTrue(square1.equals(square3));
		assertFalse(square1.equals(square2));
		
		
		// This is the test for new maze map 
		boolean[] myPassable = new boolean[27*21];
		for (int i = 0; i< myPassable.length; i++)
			myPassable[i] = true;
		
		MazeMap myMap = new MazeMap(21,27,myPassable);
		Square mySquare = Square.of(myMap, 1, 1);
		
		assertEquals(myMap, mySquare.getMazeMap());
		assertEquals(1, mySquare.getRowIndex());
		assertEquals(1, mySquare.getColumnIndex());
		assertTrue(mySquare.isPassable());
		}
		
}
