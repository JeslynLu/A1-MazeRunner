package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DirectionManager {
    private static final Logger logger = LogManager.getLogger();
    private final Direction startingDir = Direction.EAST; // assuming entry on always on West border, you always start facing East
    private Direction currentDir;

    public enum Direction{ // direction you are facing
        NORTH,
        EAST,
        WEST,
        SOUTH
    }

    public DirectionManager(){
        currentDir = startingDir;
    }

    public Direction turnRight(){
        switch(currentDir){
            case NORTH:
                currentDir = Direction.EAST;
                break;
            case EAST:
                currentDir = Direction.SOUTH;
                break;
            case WEST:
                currentDir = Direction.NORTH;
                break;
            case SOUTH:
                currentDir = Direction.WEST;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currentDir);
        }

        return currentDir;
    }

    public Direction turnLeft(){
        switch(currentDir){
            case NORTH:
                currentDir = Direction.WEST;
                break;
            case EAST:
                currentDir = Direction.NORTH;
                break;
            case WEST:
                currentDir = Direction.SOUTH;
                break;
            case SOUTH:
                currentDir = Direction.EAST;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currentDir);
        }
        return currentDir;
    }
}

    
    
