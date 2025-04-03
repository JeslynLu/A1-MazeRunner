package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Coordinates.Direction;
import ca.mcmaster.se2aa4.mazerunner.Coordinates.Position;

public class PositionTest {
    @Test
    void move() {
        Position position = new Position(5, 5).move(Direction.NORTH);
        Position newPosition = new Position(5, 4);

        assertTrue(position.equals(newPosition));
    }

    @Test
    void equals() {
        Position position = new Position(5, 5);
        Position newPosition = new Position(5, 5);

        assertTrue(position.equals(newPosition));
    }

}
