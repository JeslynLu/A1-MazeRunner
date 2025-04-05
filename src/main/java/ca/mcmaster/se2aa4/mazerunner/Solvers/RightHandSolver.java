package ca.mcmaster.se2aa4.mazerunner.Solvers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Coordinates.Position;
import ca.mcmaster.se2aa4.mazerunner.Maze.MazeNavigator;
import ca.mcmaster.se2aa4.mazerunner.MazeExplorer.Explorer;
import ca.mcmaster.se2aa4.mazerunner.MazeExplorer.ExplorerManager;
import ca.mcmaster.se2aa4.mazerunner.MoveCommands.ForwardCommand;
import ca.mcmaster.se2aa4.mazerunner.MoveCommands.TurnLeftCommand;
import ca.mcmaster.se2aa4.mazerunner.MoveCommands.TurnRightCommand;
import ca.mcmaster.se2aa4.mazerunner.Path.PathFormatter;
import ca.mcmaster.se2aa4.mazerunner.Path.PathWriter;

/**
 * Jeslyn Lu
 * lu196
 * RightHandSolver implements Solver and uses the right-hand rule algorithm to solve a maze
 */

public class RightHandSolver implements Solver {
    private static final Logger logger = LogManager.getLogger(RightHandSolver.class);

    // solve solves the given maze using the right-hand rule algorithm to return the solution path
    @Override
    public PathFormatter solve(MazeNavigator maze){
        Explorer explorer = new Explorer(maze.getEntry());
        PathWriter pathWriter = new PathWriter(explorer);
        ExplorerManager explorerManager= new ExplorerManager();
        Position currentPos = explorer.getPosition();

        while(!currentPos.equals(maze.getExit())){
            // temporary positions for turning and moving forward
            Position forwardPos = explorer.getNextForwardPosition();
            Position rightTurnPos = explorer.getNextRightTurnPosition();

            // if can move forward and supported by a wall on its right
            if(maze.isValid(forwardPos) && !maze.isValid(rightTurnPos)){
                explorerManager.executeCommand(new ForwardCommand(explorer));
            } 

            // if can turn right and move forward
            else if(maze.isValid(rightTurnPos)){
                explorerManager.executeCommand(new TurnRightCommand(explorer));
                explorerManager.executeCommand(new ForwardCommand(explorer));
            }

            else{
                explorerManager.executeCommand(new TurnLeftCommand(explorer));
            }
            logger.debug("Current Pos: " + currentPos.toString());
            currentPos = explorer.getPosition();
        }
        return pathWriter.getPath();
    }
}