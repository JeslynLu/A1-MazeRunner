package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Solver {
    protected static final Logger logger = LogManager.getLogger();
    
    public Solver(){
        
    }

    public MazePath solve(Maze maze){
        Position currentPos = maze.getEntry();
        MazePath path = new MazePath();
        path.addInstruct("FFFFLFFFFRFFFF");
        return path;
    }
}