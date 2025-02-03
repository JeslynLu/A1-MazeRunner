package ca.mcmaster.se2aa4.mazerunner;
import java.util.HashMap;

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
    private static final HashMap<Direction, Direction> rightTurns = new HashMap<>();
    static {
        rightTurns.put(NORTH, EAST); 
        rightTurns.put(EAST, SOUTH); 
        rightTurns.put(SOUTH, WEST); 
        rightTurns.put(WEST, NORTH); 
    }

    // stores the result of turning left
    private static final HashMap<Direction, Direction> leftTurns = new HashMap<>();
    static {
        leftTurns.put(NORTH, WEST);  
        leftTurns.put(EAST, NORTH);  
        leftTurns.put(SOUTH, EAST);  
        leftTurns.put(WEST, SOUTH);  
    }

    // turnRight returns the direction you would face after turning right
    public Direction turnRight() {
        return rightTurns.get(this); 
    }

    // turnLeft returns the direction after turning left
    public Direction turnLeft() {
        return leftTurns.get(this);
    }
}
    
    
