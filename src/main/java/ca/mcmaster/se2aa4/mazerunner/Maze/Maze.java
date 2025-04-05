package ca.mcmaster.se2aa4.mazerunner.Maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Coordinates.Position;

/**
 * Jeslyn Lu
 * lu196
 * Maze represents the structure of the maze, including its layout, passages, walls, entry, and exit points
 */

public class Maze implements MazeNavigator {
    private static final Logger logger = LogManager.getLogger(Maze.class);
    private ArrayList<ArrayList<Cell>> maze = new ArrayList<>(); // 2D list to represent a maze
    private final Position entry; // maze entrance
    private final Position exit; // maze exit
    private enum Cell { // two possible types of maze cells
        WALL, PASSAGE
    }

    public Maze(String filePath) throws Exception{
        this.maze = parseMaze(filePath);
        this.entry = findEntry();
        this.exit = findExit();
    }

    //  parseMaze returns 2D list with extracted maze walls and passages from inputted maze file
    private ArrayList<ArrayList<Cell>> parseMaze(String filePath) throws Exception{
        logger.info("**** Reading the maze from file " + filePath);  
        ArrayList<ArrayList<Cell>> parsedMaze = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)) // reads from retreived file
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<Cell> mazeRow = new ArrayList<>();
                
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        mazeRow.add(Cell.WALL); // wall
                    } else if (line.charAt(idx) == ' ') {
                        mazeRow.add(Cell.PASSAGE); // passage
                    }
                }
                
                // ensure row meets required length after parsing
                if (!parsedMaze.isEmpty() && mazeRow.size() < parsedMaze.get(0).size()) {
                    int requiredLen = parsedMaze.get(0).size();
                    while (mazeRow.size() < requiredLen) {
                        mazeRow.add(Cell.PASSAGE); // fill missing spaces with passages
                    }
                }
                parsedMaze.add(mazeRow);
            }
        }
        return parsedMaze;
    }

    //  findEntry returns entrance of maze - passage on the West border
    private Position findEntry(){
        Position pos = null;

        for(int i = 0; i < maze.size(); i++){ // accessing first col, which is West border
            Cell cell = maze.get(i).get(0); 
            
            if(isPassage(cell)){
                pos = new Position(0, i);
                logger.info("Found entrance at " + pos.toString());
                break;
            }
        }
        return pos;
    }

    //  findExit returns exit of maze - passage on the East border
    private Position findExit(){
        Position pos = null;
        int mazeWidth = this.maze.get(0).size() - 1;

        for(int i = 0; i < maze.size(); i++){ // accessing last col, which is East border
            Cell cell = maze.get(i).get(mazeWidth); 

            if (isPassage(cell)) {
                pos = new Position(mazeWidth, i);
                logger.info("Found exit at " + pos.toString());
                break;
            }
        }
        return pos;
    }

    // isPassage checks if a given cell is a passage
    private boolean isPassage(Cell cell) {
        return cell == Cell.PASSAGE;
    }

    @Override
    public boolean isValid(Position pos){
        return isPassage(pos) && inBounds(pos);
    }

    public boolean isPassage(Position pos) {
        if (this.inBounds(pos)) {
            // get Cell enum at the given position
            Cell cell = maze.get(pos.getY()).get(pos.getX());
            return cell == Cell.PASSAGE; 
        } else {
            logger.error("Position out of bounds: " + pos);
            return false;
        }
    }

    // inBounds checks if the position is within the maze bounds
    public boolean inBounds(Position pos) {
        return pos.getX() >= 0 && pos.getX() < maze.get(0).size() && pos.getY() >= 0 && pos.getY() < maze.size();
    }  

    @Override
    public Position getEntry(){
        return this.entry;
    }
    @Override
    public Position getExit(){
        return this.exit;
    }
    @Override
    public String toString(){
        StringBuilder mazeStr = new StringBuilder();

        for (ArrayList<Cell> rows : maze) {
            for (Cell cell : rows) {
                mazeStr.append(cell == Cell.PASSAGE ? "0" : "#");
            }
            mazeStr.append("\n");
        }   
        return mazeStr.toString();
    } 
}