package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Solver {
    private static final Logger logger = LogManager.getLogger();
    
    public Solver(){
        
    }

    public Path solve(Maze maze){
        Position currentPos = maze.getEntry();
        Path path = new Path();
        path.addInstruction("FFFFLFFFFRFFFF");
        return path;
    }
}