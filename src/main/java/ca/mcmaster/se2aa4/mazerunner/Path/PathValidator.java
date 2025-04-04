package ca.mcmaster.se2aa4.mazerunner.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Coordinates.Direction;
import ca.mcmaster.se2aa4.mazerunner.Coordinates.Position;
import ca.mcmaster.se2aa4.mazerunner.Maze.MazeNavigator;

/**
 * Jeslyn Lu
 * lu196
 * PathValidator represents a validator of whether a given path follows a valid route through the maze from an entry to an exit
 */

public class PathValidator {
    private static final Logger logger = LogManager.getLogger();
    private final MazeNavigator maze; // maze to check path for

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
        Position currentPos = newEntry;
        int index = 0;

        while(index < path.length()){
            char instruct = path.charAt(index); // get current instruction

            // executing given instructions
            if (instruct == 'F') {
                Position move = currentPos.move(dir);
                // check if the move is valid - within bounds and is a passage
                if(maze.isValid(move)){
                    currentPos = move;
                }
                else{
                    logger.error("Invalid move: " + move.toString() + " in direction " + dir);
                    return false;
                }  
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
            index ++;
        }
        logger.info("Current position: "+ currentPos.toString());
        return currentPos.equals(newExit);
    }
}
