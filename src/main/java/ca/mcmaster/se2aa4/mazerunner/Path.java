package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Path {
    private static final Logger logger = LogManager.getLogger();
    private String path;

    public Path(){
        
    }

    public String getCanonicalPath(){
        return this.path;
    }

    public String getFactorizedPath(){
        return this.path;
    }


}