package ca.mcmaster.se2aa4.mazerunner;

/**
 * Jeslyn Lu
 * lu196
 * Solver is an interface for solving a maze
 */

public interface Solver {
    // solve solves the given maze and returns the path taken
    MazePath solve(MazeNavigator maze);
    
} 
