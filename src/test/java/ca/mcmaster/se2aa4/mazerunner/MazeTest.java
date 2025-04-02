package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeTest {
    private Maze maze;
    private String mazeFile;

    @BeforeEach
    void createMaze() throws Exception{
        mazeFile = "./examples/small.maz.txt";
        maze = new Maze(mazeFile);
    }

    @Test
    void findEntry(){
        Position entry = new Position(0, 8);
        assertTrue(entry.equals(maze.getEntry()));
    }

    @Test
    void findExit(){
        Position exit = new Position(10, 5);
        assertTrue(exit.equals(maze.getExit()));
    }

    @Test
    void isPassage() {
        Position passagePos = new Position(1, 1);
        assertTrue(maze.isPassage(passagePos));

        Position wallPos = new Position(0, 0);
        assertFalse(maze.isPassage(wallPos));
    }

    @Test
    void inBounds() {
        assertTrue(maze.inBounds(new Position(2, 2))); 
        assertFalse(maze.inBounds(new Position(-1, 2))); 
        assertFalse(maze.inBounds(new Position(2, 11)));
    }

    @Test
    void mazeString() throws Exception{
        mazeFile = "./examples/tiny.maz.txt";
        maze = new Maze(mazeFile);
        String expectedMaze =
        """
        #######\n#000000\n###0###\n#00000#\n###0###\n000000#\n#######
        """;
        assertEquals(expectedMaze, maze.toString());
    }

}