package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandSolver extends Solver{

    public RightHandSolver(){
        super();
    }

    public MazePath solve(Maze maze){
        Position currentPos = maze.getEntry();
        Direction dir = Direction.EAST; // assuming entry on always on West border
        MazePath path = new MazePath();

        while(!currentPos.equals(maze.getExit())){
            
            // if can move forward and supported by a wall
            if(maze.isPassage(currentPos.move(dir)) && maze.isWall(currentPos.move(dir.turnRight()))){
                path.addInstruct("F");
                currentPos = currentPos.move(dir);
            } 
            else if(maze.isPassage(currentPos.move(dir.turnRight()))){
                dir = dir.turnRight();
                path.addInstruct("R");
                path.addInstruct("F");
                currentPos = currentPos.move(dir);
            }
            else if(maze.isPassage(currentPos.move(dir.turnLeft()))){
                dir = dir.turnLeft();
                path.addInstruct("L");
                path.addInstruct("F");
                currentPos = currentPos.move(dir);
            }
            System.out.println("currentPos: " + currentPos.print() + "\n path: " + path.getCanonicalPath());
        }
        //logger.debug("currentPos: " + currentPos.print() + "\n path: " + path.getCanonicalPath());

        return path;
    }
}