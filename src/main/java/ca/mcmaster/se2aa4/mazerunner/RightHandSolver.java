package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandSolver implements  Solver{
    private static final Logger logger = LogManager.getLogger(Maze.class);

    public RightHandSolver(){
        
    }

    public MazePath solve(Maze maze){
        Position currentPos = maze.getEntry();
        Direction dir = Direction.EAST; // assuming entry on always on West border
        MazePath path = new MazePath();

        while(!currentPos.equals(maze.getExit())){
            // temporary positions for turning and moving forward
            Position forward = currentPos.move(dir);
            Position rightTurn = currentPos.move(dir.turnRight());
            Position leftTurn = currentPos.move(dir.turnLeft());

            // if can move forward and supported by a wall on its right
            if(maze.isPassage(forward) && !maze.isPassage(rightTurn)){
                path.addInstruct("F");
                currentPos = forward;
            } 

            // if can turn right and move forward
            else if(maze.isPassage(rightTurn)){
                dir = dir.turnRight();
                path.addInstruct("R");
                path.addInstruct("F");
                currentPos = rightTurn;
            }

            // else turn left
            else{
                dir = dir.turnLeft();
                path.addInstruct("L");
            }
            logger.debug("Current Pos: " + currentPos.toString() + "\n Path: " + path.getCanonical() + "\n");
        }
        return path;
    }
}