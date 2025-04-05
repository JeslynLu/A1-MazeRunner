package ca.mcmaster.se2aa4.mazerunner.Solvers;

import ca.mcmaster.se2aa4.mazerunner.Maze.MazeNavigator;
import ca.mcmaster.se2aa4.mazerunner.Path.PathFormatter;

/**
 * Jeslyn Lu
 * lu196
 * Solver is an interface for solving a maze
 */

public interface Solver {
    // solve solves the given maze and returns the path taken
    PathFormatter solve(MazeNavigator maze);   
} 
