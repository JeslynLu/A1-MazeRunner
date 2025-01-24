package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze  {
    private static final Logger logger = LogManager.getLogger();

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
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    mazeRow.add(0); // wall
                } else if (line.charAt(idx) == ' ') {
                    mazeRow.add(1); // passage
                }
            }
                maze.add(mazeRow);
        }
        return maze;
    }

    /*  finds entrance of maze
        parameters - none
        returns - position of passage on the West border
    */ 
    public Position findEntry(){
        Position pos = new Position(0,0);
        return pos;
    }

    /*  finds exit of maze
        parameters - none
        returns - position of passage on the East border
    */ 
    public Position findExit(){
        Position pos = new Position(0,0);
        return pos;
    }

    public Integer get(int x, int y){
        return maze.get(x).get(y);
    }
}