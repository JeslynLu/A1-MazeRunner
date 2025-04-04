package ca.mcmaster.se2aa4.mazerunner.Solvers;

import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Coordinates.Direction;
import ca.mcmaster.se2aa4.mazerunner.Coordinates.Position;
import ca.mcmaster.se2aa4.mazerunner.Explorer;
import ca.mcmaster.se2aa4.mazerunner.Maze.MazeNavigator;
import ca.mcmaster.se2aa4.mazerunner.MoveCommands.Command;
import ca.mcmaster.se2aa4.mazerunner.MoveCommands.ForwardCommand;
import ca.mcmaster.se2aa4.mazerunner.MoveCommands.TurnCommand;
import ca.mcmaster.se2aa4.mazerunner.Path.MazePath;
import ca.mcmaster.se2aa4.mazerunner.Subject;

/**
 * Jeslyn Lu
 * lu196
 * RightHandSolver implements Solver and uses the right-hand rule algorithm to solve a maze
 */

public class RightHandSolver extends Subject implements Solver {
    private static final Logger logger = LogManager.getLogger(RightHandSolver.class);
    private Stack<Command> commandHistory = new Stack<>();
    private int state;

    public void executeCommand(Command command){
        command.execute();
        commandHistory.push(command);
    }

    // solve solves the given maze using the right-hand rule algorithm to return the solution path
    @Override
    public MazePath solve(MazeNavigator maze, Explorer explorer){
        Position currentPos = explorer.getPosition();
        logger.debug("Current Pos: " + currentPos.toString());
        Direction dir = explorer.getDirection();
        logger.debug("Current Dir: " + dir.toString());
        MazePath path = new MazePath();

        while(!currentPos.equals(maze.getExit())){
            // temporary positions for turning and moving forward
            Position forwardPos = currentPos.move(dir);
            Position rightTurnPos = currentPos.move(dir.turnRight());

            // if can move forward and supported by a wall on its right
            if(maze.isPassage(forwardPos) && !maze.isPassage(rightTurnPos)){
                executeCommand(new ForwardCommand(explorer, forwardPos));
                path.addInstruct("F");
            } 

            // if can turn right and move forward
            else if(maze.isPassage(rightTurnPos)){
                executeCommand(new TurnCommand(explorer, dir.turnRight()));
                executeCommand(new ForwardCommand(explorer, rightTurnPos));
                path.addInstruct("R");
                path.addInstruct("F");
            }

            else{
                executeCommand(new TurnCommand(explorer, dir.turnLeft()));
                path.addInstruct("L");
            }
            logger.debug("Current Pos: " + currentPos.toString() + "\n Path: " + path.getCanonical() + "\n");
            currentPos = explorer.getPosition();
            dir = explorer.getDirection();
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