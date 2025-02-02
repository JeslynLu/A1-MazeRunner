package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazePath {
    private static final Logger logger = LogManager.getLogger();
    private StringBuilder path;

    public MazePath(){
        this.path = new StringBuilder("");
    }

    public MazePath(String path){
        String cleanPath = expandFactorized(path);
        pathFormatCheck(cleanPath);
        this.path = new StringBuilder(cleanPath);
    }

    public void addInstruct(String instruct){
        path.append(instruct);
    }

    public char getInstruct(){
        return this.path.charAt(0);
    }

    public boolean pathFormatCheck(String newPath){
        if(newPath.equals("")){
            throw new IllegalArgumentException("Path cannot be empty.");
        }
        for(int i=0; i<newPath.length(); i++){
            char instruct = newPath.charAt(i);
            if(instruct != ' ' && instruct != 'F' && instruct != 'L' && instruct != 'R'){
                throw new IllegalArgumentException("\"" + instruct + "\" is not a valid path instruction.");
            }
        }
        return true;
    }

    public String expandFactorized(String newPath){
        StringBuilder expanded = new StringBuilder("");

        for(int i=0; i<newPath.length(); i++){
            char current = newPath.charAt(i);
            
            if(Character.isWhitespace(current)){ // ignore spaces
                expanded.append("");
            }

            else if(Character.isDigit(current)){
                int num = Character.getNumericValue(current); // digit value
                char nextInstruct = path.charAt(i+1); // instruction followed by digit

                for(int j=0; j < num; j++){
                    expanded.append(nextInstruct);
                }
                i++;
            }
            else{
                expanded.append(current);
            }
        } 
        logger.info(expanded.toString());
        return expanded.toString();
    }
    

    public String getCanonical(){ // formatting canonical path form
        StringBuilder canonical = new StringBuilder("");

        for(int i=0; i<path.length(); i++){
            char current = path.charAt(i); // current instruction type

            while(i<path.length() && path.charAt(i) == current){
                canonical.append(current);
                i++;
            }

            canonical.append(" ");
            i--;
        } 
        return canonical.toString();
    }

    public String getFactorized(){ // converting canonical to factorized form
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

            factorized.append(current + " ");
            i--;
        } 
        return factorized.toString();
    }

    public int getSize(){
        return this.path.length();
    }
}