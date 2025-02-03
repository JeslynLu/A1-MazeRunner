package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

            logger.info("\n\n"+maze.toString());

            Solver solver = new RightHandSolver();
            MazePath path = solver.solve(maze);

            System.out.println("Canonical form: " + path.getCanonical());
            System.out.println("Factorized form: " + path.getFactorized());

            if(cmd.getOptionValue("p")!= null){
                logger.info("Validating maze path");
                MazePath pathToCheck = new MazePath(cmd.getOptionValue("p"));
                boolean validPath = maze.checkPath(pathToCheck);

                if(validPath){
                    System.out.println("correct path");
                }
                else{
                    System.out.println("incorrect path");
                }
            }

            
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
            logger.error(e.getMessage());
            logger.error("PATH NOT COMPUTED");
        }
    
        logger.info("** End of MazeRunner");
    }

}
