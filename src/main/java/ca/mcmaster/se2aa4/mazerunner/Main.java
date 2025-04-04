package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Path.MazePath;
import ca.mcmaster.se2aa4.mazerunner.Path.PathValidator;
import ca.mcmaster.se2aa4.mazerunner.Solvers.RightHandSolver;
import ca.mcmaster.se2aa4.mazerunner.Solvers.Solver;

/**
 * Jeslyn Lu
 * lu196
 * MazeRunner
 */

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

            if(cmd.getOptionValue("p")!= null){ // verifying inputted path only
                logger.info("Validating maze path");
                
                MazePath pathToCheck = new MazePath(cmd.getOptionValue("p"));
                PathValidator validator = new PathValidator(maze);
                boolean validPath = validator.checkPath(pathToCheck);

                if(validPath){
                    System.out.println("correct path");
                }
                else{
                    System.out.println("incorrect path");
                }
            }

            else{ // finding path for maze only
                logger.info("**** Computing path");
                logger.info("\n\n" + maze.toString());

                Solver solver = chooseSolver();
                logger.info("**** Solver chosen");
                MazePath path = solver.solve(maze);
                System.out.println(path.getFactorized());
            }
    
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
            logger.error(e.getMessage());
            logger.error("PATH NOT COMPUTED");
        }
    
        logger.info("** End of MazeRunner");
    }

    // chooseSolver returns specified Solver object
    public static Solver chooseSolver(){
        return new RightHandSolver();
    }

}
