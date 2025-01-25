package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Path {
    private static final Logger logger = LogManager.getLogger();
    private StringBuilder path;

    public Path(){
        this.path = new StringBuilder("");
    }

    public void addInstruction(String instruct){
        path.append(instruct);

    }

    public StringBuilder getCanonicalPath(){
        return this.path;
    }

    public StringBuilder getFactorizedPath(){
        return this.path;
    }


}