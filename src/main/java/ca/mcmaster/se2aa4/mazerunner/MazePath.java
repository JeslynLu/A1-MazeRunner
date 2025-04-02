package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Jeslyn Lu
 * lu196
 * MazePath represents a path through the maze using a sequence of movement instructions
 */

public class MazePath {
    private static final Logger logger = LogManager.getLogger();
    private StringBuilder path; // sequence of instructions

    public MazePath(){
        this.path = new StringBuilder("");
    }

    // Constructor cleans up and validates the input format
    public MazePath(String newPath){
        String cleanPath = expandFactorized(newPath); // cleaning up path input
        pathFormatCheck(cleanPath); // ensuring path input format is valid
        this.path = new StringBuilder(cleanPath);
    }

    // pathFormatCheck returns if only valid instructions are in inputted string
    public boolean pathFormatCheck(String newPath){
        if(newPath.equals("")){
            logger.error("Path cannot be empty.");
            return false;
        }
        // ensuring path characters are valid ones
        for(int i = 0; i < newPath.length(); i++){
            char instruct = newPath.charAt(i);
            if(instruct != ' ' && instruct != 'F' && instruct != 'L' && instruct != 'R'){
                logger.error("\"" + instruct + "\" is not a valid path instruction.");
                return false;
            }
        }
        return true;
    }

    // expandFactorized expands a factorized path string into a normal form
    public String expandFactorized(String newPath){
        StringBuilder expanded = new StringBuilder("");

        for(int i = 0; i < newPath.length(); i++){
            char current = newPath.charAt(i);
            
            if(Character.isWhitespace(current)){ // ensures no spaces are added
            }

            else if(Character.isDigit(current)){
                int num = 0;
                // getting digit value
                while(i<newPath.length() && Character.isDigit(newPath.charAt(i))) {
                    num = num * 10 + Character.getNumericValue(newPath.charAt(i));
                    i++;
                }

                if (i<newPath.length()) {
                    char nextInstruct = newPath.charAt(i);
    
                    for (int j = 0; j < num; j++) {
                        expanded.append(nextInstruct);
                    }
                }
            } 
            else {
                expanded.append(current);
            }
        }
        return expanded.toString();
    }

    // getExpanded returns the expanded version of the stored path
    public String getExpanded(){
        return this.path.toString();
    }

    // getCanonical returns the canonical path formatted with spaces
    public String getCanonical(){ 
        StringBuilder canonical = new StringBuilder("");

        for(int i = 0; i < path.length(); i++){
            char current = path.charAt(i); // Current instruction type

            while(i < path.length() && path.charAt(i) == current){
                canonical.append(current);
                i++;
            }

            if(i != path.length()){ // Adding spaces between different instruction types
                canonical.append(" ");
            }

            i--;
        } 
        return canonical.toString();
    }

    // getFactorized converts the canonical path into a factorized format and returns factorized form
    public String getFactorized(){ 
        StringBuilder factorized = new StringBuilder("");
        int i = 0;

        while(i < path.length()){
            char current = path.charAt(i);
            int count = 1; // Count occurrences of the current instruction

            while((i+1) < path.length() && path.charAt(i+1) == current){
                count++;
                i++;
            }

            if(count > 1){
                factorized.append(count);
            }
            factorized.append(current);

            if(i + 1 < path.length()){ // If not at last instruction
                factorized.append(" ");
            }

            i++;
        } 
        return factorized.toString();
    }

    // addInstruct adds inputted instruction onto end of path
    public void addInstruct(String instruct){
        path.append(instruct);
    }

    // getInstruct returns first instruction of path
    public char getInstruct(){
        return this.path.charAt(0);
    }
}
