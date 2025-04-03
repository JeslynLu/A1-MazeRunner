package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Coordinates.Direction;

public class DirectionTest {
    @Test
    void turnRight() {
        Direction direction = Direction.EAST;

        assertEquals(Direction.SOUTH, direction.turnRight());
    }

    @Test
    void turnLeft() {
        Direction direction = Direction.EAST;

        assertEquals(Direction.NORTH, direction.turnLeft());
    }
}