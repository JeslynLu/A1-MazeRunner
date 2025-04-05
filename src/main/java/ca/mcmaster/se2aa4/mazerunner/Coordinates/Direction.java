package ca.mcmaster.se2aa4.mazerunner.Coordinates;
import java.util.Map;

/**
 * Jeslyn Lu
 * lu196
 * Direction enum represents the four possible directions you can face in the maze
 */

public enum Direction{ // direction you are facing
        NORTH,
        EAST,
        WEST,
        SOUTH;

    // stores the result of turning right for each direction
    private static final Map<Direction, Direction> RIGHT_TURNS = Map.of(
        NORTH, EAST,
        EAST, SOUTH,
        SOUTH, WEST,
        WEST, NORTH
    );

    // stores the result of turning left
    private static final Map<Direction, Direction> LEFT_TURNS = Map.of(
        NORTH, WEST, 
        EAST, NORTH,
        SOUTH, EAST,
        WEST, SOUTH  
    );

    private static final Map<Direction, Direction> BACK_TURNS = Map.of(
        NORTH, SOUTH, 
        EAST, WEST,
        SOUTH, NORTH, 
        WEST, EAST 
    );

    // turnRight returns the direction you would face after turning right
    public Direction turnRight() {
        return RIGHT_TURNS.get(this); 
    }

    // turnLeft returns the direction after turning left
    public Direction turnLeft() {
        return LEFT_TURNS.get(this);
    }

    public Direction turnBack() {
        return BACK_TURNS.get(this);
    }
}
    
    
