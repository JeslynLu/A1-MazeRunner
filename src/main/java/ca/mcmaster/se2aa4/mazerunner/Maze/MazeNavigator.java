package ca.mcmaster.se2aa4.mazerunner.Maze;

import ca.mcmaster.se2aa4.mazerunner.Coordinates.Position;

/**
 * Jeslyn Lu
 * lu196
 * MazeNavigator is interface for basic maze operations
 */

public interface MazeNavigator {
    Position getEntry();
    Position getExit();
    boolean isValid(Position pos);
}
