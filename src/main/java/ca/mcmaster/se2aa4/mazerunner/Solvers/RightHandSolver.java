package ca.mcmaster.se2aa4.mazerunner.Solvers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Coordinates.Position;
import ca.mcmaster.se2aa4.mazerunner.ExplorerMarker.Explorer;
import ca.mcmaster.se2aa4.mazerunner.ExplorerMarker.ExplorerManager;
import ca.mcmaster.se2aa4.mazerunner.Maze.MazeNavigator;
import ca.mcmaster.se2aa4.mazerunner.MoveCommands.ForwardCommand;
import ca.mcmaster.se2aa4.mazerunner.MoveCommands.TurnLeftCommand;
import ca.mcmaster.se2aa4.mazerunner.MoveCommands.TurnRightCommand;
import ca.mcmaster.se2aa4.mazerunner.Path.MazePath;
import ca.mcmaster.se2aa4.mazerunner.Subject;

/**
 * Jeslyn Lu
 * lu196
 * RightHandSolver implements Solver and uses the right-hand rule algorithm to solve a maze
 */

public class RightHandSolver extends Subject implements Solver {
    private static final Logger logger = LogManager.getLogger(RightHandSolver.class);
    private int state;

    // solve solves the given maze using the right-hand rule algorithm to return the solution path
    @Override
    public MazePath solve(MazeNavigator maze){
        MazePath path = new MazePath();
        Explorer explorer = new Explorer(maze.getEntry());
        ExplorerManager explorerManager= new ExplorerManager();
        Position currentPos = explorer.getPosition();

        while(!currentPos.equals(maze.getExit())){
            // temporary positions for turning and moving forward
            Position forwardPos = explorer.getNextForwardPosition();
            Position rightTurnPos = explorer.getNextRightTurnPosition();

            // if can move forward and supported by a wall on its right
            if(maze.isValid(forwardPos) && !maze.isValid(rightTurnPos)){
                explorerManager.executeCommand(new ForwardCommand(explorer));
                path.addInstruct("F");
            } 

            // if can turn right and move forward
            else if(maze.isValid(rightTurnPos)){
                explorerManager.executeCommand(new TurnRightCommand(explorer));
                explorerManager.executeCommand(new ForwardCommand(explorer));
                path.addInstruct("R");
                path.addInstruct("F");
            }

            else{
                explorerManager.executeCommand(new TurnLeftCommand(explorer));
                path.addInstruct("L");
            }
            logger.debug("Current Pos: " + currentPos.toString());
            currentPos = explorer.getPosition();
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