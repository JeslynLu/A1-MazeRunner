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
        StringBuilder factorized = new StringBuilder("");

        for(int i=0; i<path.length(); i++){
            char current = path.charAt(i);
            int count = 0; // count of the current instruction type

            while(i<path.length() && path.charAt(i) == current){
                count++;
                i++;
            }

            if(count > 1){
                factorized.append(count);
            }
            factorized.append(current);

            i--;
        } 
        return factorized.toString();
    }

    public int getSize(){
        return this.path.length();
    }
}