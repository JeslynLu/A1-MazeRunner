package ca.mcmaster.se2aa4.mazerunner.Solvers;

import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Commands.Command;
import ca.mcmaster.se2aa4.mazerunner.Coordinates.Direction;
import ca.mcmaster.se2aa4.mazerunner.Coordinates.Position;
import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.MazeNavigator;
import ca.mcmaster.se2aa4.mazerunner.Path.MazePath;
import ca.mcmaster.se2aa4.mazerunner.Subject;

/**
 * Jeslyn Lu
 * lu196
 * RightHandSolver implements Solver and uses the right-hand rule algorithm to solve a maze
 */

public class RightHandSolver extends Subject implements Solver {
    private static final Logger logger = LogManager.getLogger(Maze.class);
    private Stack<Command> commandHistory = new Stack<>();
    private int state;

    public void move(Command newMove){
        newMove.execute();
        commandHistory.push(newMove);
    }

    // solve solves the given maze using the right-hand rule algorithm to return the solution path
    public MazePath solve(MazeNavigator maze){
        Position currentPos = maze.getEntry();
        Direction dir = Direction.EAST; // assuming entry on always on West border
        MazePath path = new MazePath();

        while(!currentPos.equals(maze.getExit())){
            // temporary positions for turning and moving forward
            Position forward = currentPos.move(dir);
            Position rightTurn = currentPos.move(dir.turnRight());

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

    public int getState(){
        return this.state;
    }

    public void setState(int state){
        this.state = state;
        this.notifyAllObservers();
    }
}