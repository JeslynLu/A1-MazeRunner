package ca.mcmaster.se2aa4.mazerunner.Coordinates;
import java.util.HashMap;
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
    private static final Map<Direction, Direction> RIGHT_TURNS = new HashMap<>();
    static {
        RIGHT_TURNS.put(NORTH, EAST); 
        RIGHT_TURNS.put(EAST, SOUTH); 
        RIGHT_TURNS.put(SOUTH, WEST); 
        RIGHT_TURNS.put(WEST, NORTH); 
    }

    // stores the result of turning left
    private static final Map<Direction, Direction> LEFT_TURNS = new HashMap<>();
    static {
        LEFT_TURNS.put(NORTH, WEST);  
        LEFT_TURNS.put(EAST, NORTH);  
        LEFT_TURNS.put(SOUTH, EAST);  
        LEFT_TURNS.put(WEST, SOUTH);  
    }

    private static final Map<Direction, Direction> BACK_TURNS = new HashMap<>();
    static {
        BACK_TURNS.put(NORTH, SOUTH);  
        BACK_TURNS.put(EAST, WEST);  
        BACK_TURNS.put(SOUTH, NORTH);  
        BACK_TURNS.put(WEST, EAST);  
    }

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
    
    
