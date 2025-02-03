package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Jeslyn Lu
 * lu196
 * PathValidator represents a validator of whether a given path follows a valid route through the maze from an entry to an exit
 */

public class PathValidator {
    private static final Logger logger = LogManager.getLogger();
    private MazeNavigator maze; // maze to check path for

    public PathValidator(MazeNavigator maze){
        this.maze = maze;
    }

    // checkPath returns if given path is valid by checking path with both possible entries (West and East)
    public boolean checkPath(MazePath path){ 
        String pathStr = path.getExpanded();
        
        logger.info("Checking path: " + pathStr);

        // checking for entrance/exit directed both ways
        boolean validWestEntry = checkPath(maze.getEntry(), maze.getExit(), Direction.EAST, pathStr);
        boolean validEastEntry = checkPath(maze.getExit(), maze.getEntry(), Direction.WEST, pathStr); 

        logger.info("Path check results - East: " + validWestEntry + ", West: " + validEastEntry);

        return validWestEntry || validEastEntry;
    }

    // checkPath returns if given path is valid from a specific entry to exit with a given direction
    public boolean checkPath(Position newEntry, Position newExit, Direction dir, String path){
        StringBuilder copyPath = new StringBuilder(path);
        Position currentPos = newEntry;
        Position move = null;

        while(copyPath.length() > 0){
            char instruct = copyPath.charAt(0); // get current instruction

            // executing given instructions
            if (instruct == 'F') {
                move = currentPos.move(dir);
            } 
            else if (instruct == 'L') {
                dir = dir.turnLeft();
            } 
            else if (instruct == 'R') {
                dir = dir.turnRight();
            } 
            else {
                logger.warn("Invalid instruction found: " + instruct);
                return false;
            }

            // check if the move is valid - within bounds and is a passage
            if(maze.inBounds(move) && maze.isPassage(move)){
                currentPos = move;
                copyPath.deleteCharAt(0);
            }
            else{
                logger.error("Invalid move: " + move.toString() + " in direction " + dir);
                return false;
            }  
        }

        return currentPos.equals(newExit);
    }
}
