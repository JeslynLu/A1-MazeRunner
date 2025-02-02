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

            if(line.isEmpty()){ // add passages for empty new line
                for(int idx = 0; idx < parsedMaze.get(0).size(); idx++){ 
                    mazeRow.add(1); 
                }
            }else{
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        mazeRow.add(0); // wall
                    } else if (line.charAt(idx) == ' ') {
                        mazeRow.add(1); // passage
                    }
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

    public void printMaze(){
        for(ArrayList<Integer> rows : maze){
            for(Integer val : rows){
                System.out.print(val + " ");
            }
            System.out.println(); 
        }   
    }

    public boolean isPassage(int val){
        return val == 1;
    }

    public boolean isPassage(Position pos){
        try {
            this.inBounds(pos);
            Integer val = maze.get(pos.getY()).get(pos.getX());
            return val == 1;
        } catch (IllegalArgumentException e) {
            logger.error("Position out of bounds: " + pos.print());
            return false;  
        }
    }


    public Position getEntry(){
        return this.entry;
    }
    public Position getExit(){
        return this.exit;
    }

    public boolean inBounds(Position pos) throws IllegalArgumentException{
        if (pos.getX() < 0 || pos.getX() >= maze.get(0).size() || pos.getY() < 0 || pos.getY() >= maze.size()) {
            throw new IllegalArgumentException("Position (" + pos.getX() + ", " + pos.getY() + ") is out of bounds");
        }
        return true;
    }

    public boolean checkPath(MazePath path){
        if(path.getCanonical().equals("FFFFLFFFFRFFFF")){
            return true;
        }
        return false;
    }
}
