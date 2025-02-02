package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze  {
    private static final Logger logger = LogManager.getLogger(Maze.class);

    private ArrayList<ArrayList<Integer>> maze = new ArrayList<>();
    private final Position entry;
    private final Position exit;

    public Maze(String filePath) throws Exception{
        this.maze = parseMaze(filePath);
        this.entry = findEntry();
        this.exit = findExit();
    }

    /*  extracts maze walls and passages from maze file
        parameters - maze file to extract info from
        returns - 2D array list, where 0 is wall and 1 is passage
    */ 
    public ArrayList<ArrayList<Integer>> parseMaze(String filePath) throws Exception{
        logger.info("**** Reading the maze from file " + filePath);  
        ArrayList<ArrayList<Integer>> parsedMaze = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath)); // reads from retreived file
        String line;
        while ((line = reader.readLine()) != null) {
            ArrayList<Integer> mazeRow = new ArrayList<>();

            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    mazeRow.add(0); // wall
                } else if (line.charAt(idx) == ' ') {
                    mazeRow.add(1); // passage
                }
            }

            // ensure row meets required length after parsing
            if (!parsedMaze.isEmpty() && mazeRow.size() < parsedMaze.get(0).size()) {
                int requiredLen = parsedMaze.get(0).size();
                while (mazeRow.size() < requiredLen) {
                    mazeRow.add(1); // fill missing spaces with passages
                }
            }
            parsedMaze.add(mazeRow);
        }
        reader.close();
        return parsedMaze;
    }

    public Integer getVal(int x, int y){
        return maze.get(y).get(x);
    }

    /*  finds entrance of maze
        parameters - none
        returns - position of passage on the West border
    */ 
    public Position findEntry(){
        Position pos = null;

        for(int i = 0; i < maze.size(); i++){ // accessing first col, which is West border
            Integer val = maze.get(i).get(0); 
            
            if(isPassage(val)){
                pos = new Position(0, i);
                break;
            }
        }
        logger.info("Found entrance at position: (" + pos.getX() + "," + pos.getY() + ")");
        return pos;
    }

    /*  finds exit of maze
        parameters - none
        returns - position of passage on the East border
    */ 
    public Position findExit(){
        Position pos = null;
        int mazeWidth = this.maze.get(0).size() - 1;

        for(int i = 0; i < maze.size(); i++){ // accessing last col, which is East border
            Integer val = maze.get(i).get(mazeWidth); 
            
            if(isPassage(val)){
                pos = new Position(mazeWidth, i);
                break;
            }
        }
        logger.info("Found exit at position: (" + pos.getX() + "," + pos.getY() + ")");
        return pos;
    }

    public String toString(){
        StringBuilder mazeStr = new StringBuilder();

        for(ArrayList<Integer> rows : maze){
            for(Integer val : rows){
                mazeStr.append(val).append(" ");
            }
            mazeStr.append("\n");
        }   
        return mazeStr.toString();
    }

    public boolean isPassage(int val){
        return val == 1;
    }

    public boolean isPassage(Position pos) {
        if(this.inBounds(pos)){
            return maze.get(pos.getY()).get(pos.getX()) == 1; 
        }else{
            logger.error("Position out of bounds: " + pos);
            return false;
        }       
    }

    public Position getEntry(){
        return this.entry;
    }
    public Position getExit(){
        return this.exit;
    }

    public boolean inBounds(Position pos) {
        return pos.getX() >= 0 && pos.getX() < maze.get(0).size() && pos.getY() >= 0 && pos.getY() < maze.size();
    }   

    public void assertBounds(Position pos) {
        if (!inBounds(pos)) {
            throw new IllegalArgumentException("Position out of bounds: " + pos);
        }
    }   


    public boolean checkPath(MazePath path){ 
        String pathStr = path.getExpanded();
        logger.info("Checking path: " + pathStr);

        // checking for entrance/exit directed both ways
        boolean validWestEntry = checkPath(this.entry, this.exit, Direction.EAST, pathStr);
        boolean validEastEntry = checkPath(this.exit, this.entry, Direction.WEST, pathStr); 

        logger.info("Path check results - East: " + validWestEntry + ", West: " + validEastEntry);

        return validWestEntry || validEastEntry;
    }

    public boolean checkPath(Position newEntry, Position newExit, Direction dir, String path){
        StringBuilder copyPath = new StringBuilder(path);
        Position currentPos = newEntry;
        Position move = null;

        while(copyPath.length() > 0){
            char instruct = copyPath.charAt(0);

            if (instruct == 'F') {
                move = currentPos.move(dir);
            } 
            else if (instruct == 'L') {
                dir = dir.turnLeft();
            } 
            else if (instruct == 'R') {
                dir = dir.turnRight();
            } 
            else {
                logger.warn("Invalid instruction found: " + instruct);
                return false;
            }

            logger.info("checkpath " + move.toString() + " dir " + dir);

            if(this.inBounds(move) && this.isPassage(move)){
                currentPos = move;
                copyPath.deleteCharAt(0);
            }
            else{
                logger.error("Invalid move: " + move.toString() + " in direction " + dir);
                return false;
            }  
        }

        return currentPos.equals(newExit);
    }
}
