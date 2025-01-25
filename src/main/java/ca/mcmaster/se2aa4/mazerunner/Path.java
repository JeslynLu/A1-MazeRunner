package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Path {
    private static final Logger logger = LogManager.getLogger();
    private StringBuilder path;

    public Path(){
        this.path = new StringBuilder("");
    }

    public Path(String path){
        this.path = new StringBuilder(path);
    }

    public void addInstruction(String instruct){
        path.append(instruct);
    }

    public char getInstruct(){
        return this.path.charAt(0);
    }

    public String getCanonicalPath(){
        return this.path.toString();
    }

    public String getFactorizedPath(){
        return this.path.toString();
    }
}