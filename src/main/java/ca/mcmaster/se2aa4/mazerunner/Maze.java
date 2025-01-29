package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
        BufferedReader reader = new BufferedReader(new FileReader(filePath)); // reads from retreived file
        String line;
        while ((line = reader.readLine()) != null) {
            ArrayList<Integer> mazeRow = new ArrayList<>();

            if(line.isEmpty()){ // add passages for empty new line
                for(int idx = 0; idx < maze.get(0).size(); idx++){ 
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
            maze.add(mazeRow);
        }
        reader.close();
        return maze;
    }

    public Integer getVal(int x, int y){
        return maze.get(x).get(y);
    }

    /*  finds entrance of maze
        parameters - none
        returns - position of passage on the West border
    */ 
    public Position findEntry(){
        Position pos = null;

        for(int row = 0; row < maze.size(); row++){ // accessing first col, which is West border
            Integer val = maze.get(row).get(0); 
            
            if(isPassage(val)){
                pos = new Position(row,0);
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
        int mazeWidth = (maze.get(0).size()) - 1;
        for(int row = 0; row < maze.size(); row++){ // accessing first col, which is West border
            Integer val = maze.get(row).get(mazeWidth); 
            
            if(isPassage(val)){
                pos = new Position(row,mazeWidth);
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
        return maze.get(pos.getY()).get(pos.getX())==1;
    }

    public boolean isWall(Position pos){
        return maze.get(pos.getY()).get(pos.getX())==0;
    }

    public Position getEntry(){
        return this.entry;
    }
    public Position getExit(){
        return this.exit;
    }

    public boolean checkPath(MazePath path){
        if(path.getCanonicalPath().equals("FFFFLFFFFRFFFF")){
            return true;
        }
        return false;
    }
}
