package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import main.java.ca.mcmaster.se2aa4.mazerunner.Maze;
//import main.java.ca.mcmaster.se2aa4.mazerunner.Solver;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        // creating argument option for "-i" flag - solve maze input
        Options options = new Options();
        Option fileOption = new Option("i", true, "File that contains maze");
        fileOption.setRequired(true);
        options.addOption(fileOption);

        // creating argument option for "-p" flag - verify path input
        options.addOption(new Option("p", true, "Maze path to be verified"));

        CommandLineParser parser = new DefaultParser(); // parser to read command-line arguments

        try {
            CommandLine cmd = parser.parse(options, args);
            String filePath = cmd.getOptionValue('i'); // assigns maze text file to filePath
            Maze maze = new Maze(filePath);

            Solver solver = new Solver();
            Path path = solver.solve(maze);
            System.out.println("To explore the maze, follow the instructions: " + path.getCanonicalPath());

            if(cmd.getOptionValue("p")!= null){
                logger.info("Validating maze path");
                Path pathToCheck = new Path(cmd.getOptionValue("p"));
                boolean validPath = maze.checkPath(pathToCheck);

                if(validPath){
                    System.out.println("The path: " + pathToCheck.getCanonicalPath() + " is valid!");
                }
                else{
                    System.out.println("The path: " + pathToCheck.getCanonicalPath() + " is NOT valid!");
                }
            }

            
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        //logger.error("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
