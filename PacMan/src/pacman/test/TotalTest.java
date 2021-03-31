package pacman.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.Dot;
import pacman.Ghost;
import pacman.MazeMap;
import pacman.PacMan;
import pacman.Square;

class TotalTest {

	@Test
	void test() {
		// Rebuild the maze map || Ghost:G(row = 11, column = 10) || Pac-Man:P(row = 20, column = 10)
		String mazeLayout = """
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
				     .  #####  .        
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
				""";
		String[] lines = mazeLayout.trim().split("\n");
		int height = lines.length;
		int width = lines[0].length();
		boolean[] passable = new boolean[height * width];
		for (int i = 0; i < passable.length; i++)
			passable[i] = true;
		
		for (int row = 0; row < lines.length; row++) {
			String line = lines[row];
			for (int column = 0; column < line.length(); column++) {
				char c = line.charAt(column);
				if (c == '#')
					passable[row * width + column] = false;
			}
		}
		// Create MazeMap
		MazeMap map = new MazeMap(width, height, passable);
		
		int nbDots = 0;
		Dot[] dot_in_maze = new Dot[width * height];
		PacMan pacMan = null;
		Ghost ghost = null;
		
		Square square1 = Square.of(map, 1, 2);   // square1_dot
		Square square2 = Square.of(map, 1, 0);   //square2_wall
		Square square3 = Square.of(map, 1, 2);   //square3_dot
		Square ghost_square = Square.of(map, 11, 10);  //ghost1
		Square pacman_square = Square.of(map, 20, 10); //Pac-Man
		
		for (int row = 0; row < lines.length; row++) {
			String line = lines[row];
			for (int column = 0; column < line.length(); column++) {
				char c = line.charAt(column);
				switch (c) {
				case ' ' -> {}
				case '#' -> {}
				case '.' -> dot_in_maze[nbDots++] = new Dot(Square.of(map, row, column));
				// Create ghost
				case 'G' -> ghost = new Ghost(ghost_square, Direction.UP);
				// Create Pac-Man
				case 'P' -> pacMan = new PacMan(3, pacman_square);
				}
				}
			}
		//Test MazeMap
		assertEquals(27, map.getHeight());
		assertEquals(21, map.getWidth());
		assertTrue(map.isPassable(1, 2));
		assertFalse(map.isPassable(1, 0));

		//Test Dot
		Dot dot = new Dot(square1);
		assertEquals(square1, dot.getSquare());
		assertNotEquals(square1, dot_in_maze[1].getSquare());  //test representation exposure leaking
		assertTrue(dot_in_maze[1].getSquare().equals(square1)); 
		
		//Test Square
		//../Check square1 at position (row 1, column 2): passable (dot)
		assertEquals(map,square1.getMazeMap());
		assertEquals(1, square1.getRowIndex());
		assertEquals(2, square1.getColumnIndex());
		assertTrue(square1.isPassable());
		
		Square neighborRight = Square.of(map, 1, 3);
		assertNotEquals(neighborRight, square1.getNeighbor(Direction.RIGHT)); //check representation exposure leaking
		assertTrue(square1.getNeighbor(Direction.RIGHT).equals(Square.of(map, 1, 3)));
		assertTrue(square1.getNeighbor(Direction.LEFT).equals(Square.of(map, 1, 1)));
		assertTrue(square1.getNeighbor(Direction.UP).equals(Square.of(map, 0, 2)));
		assertTrue(square1.getNeighbor(Direction.DOWN).equals(Square.of(map, 2, 2)));
		
		assertFalse(square1.canMove(Direction.UP));
		assertFalse(square1.canMove(Direction.DOWN));
		assertTrue(square1.canMove(Direction.LEFT));
		assertTrue(square1.canMove(Direction.RIGHT));
		                                                                             //check possible moving direction
		assertArrayEquals(new Direction[] {Direction.RIGHT,Direction.LEFT}, square1.getPassableDirectionsExcept(Direction.DOWN));
		assertEquals(Direction.RIGHT, square1.getPassableDirectionsExcept(Direction.LEFT)[0]);
		assertEquals(Direction.LEFT, square1.getPassableDirectionsExcept(Direction.RIGHT)[0]);
		//../Check square at position (row 1, column 0): not passable (wall)
		assertFalse(square2.isPassable());
		//../Check if two square objects are equal
		assertTrue(square1.equals(square3));
		assertFalse(square1.equals(square2));
		
		//Test Ghost
		//../Test on ghost G(row = 11, column = 10) 
		assertEquals(ghost_square, ghost.getSquare());
		assertEquals(Direction.UP,ghost.getDirection());
		
		ghost.setDirection(Direction.RIGHT);
		assertEquals(Direction.RIGHT,ghost.getDirection());
		
		Square new_square = Square.of(map, 12, 13);
		ghost.setSquare(new_square);
		assertEquals(new_square,ghost.getSquare());
		
		
		//Test Pacman
		assertEquals(pacman_square, pacMan.getSquare());
		assertEquals(3, pacMan.getNbLives());

		pacMan.setSquare(new_square);
		assertEquals(new_square, pacMan.getSquare());
		
		pacMan.die();
		assertEquals(2, pacMan.getNbLives());
		
	}

}
