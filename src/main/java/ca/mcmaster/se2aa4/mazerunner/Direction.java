package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Direction {
    private static final Logger logger = LogManager.getLogger();

    public enum Moves{
        UP,
        LEFT,
        RIGHT,
        DOWN
    }

    public enum Compass{
        N,
        E,
        W,
        S
    }

    public Direction(){
        Compass dir = Compass.E; // assuming you always start facing East
        switch(dir){
            case N:
                System.out.println("Facing North");
                break;
            case E:
                System.out.println("Facing East");
                break;
            case W:
                System.out.println("Facing West");
                break;
            case S:
                System.out.println("Facing South");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dir);
        }
        }
    }

    
    
