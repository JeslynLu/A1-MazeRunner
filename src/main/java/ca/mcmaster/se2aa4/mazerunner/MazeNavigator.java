package ca.mcmaster.se2aa4.mazerunner;

/**
 * Jeslyn Lu
 * lu196
 * MazeNavigator is interface for basic maze operations
 */

public interface MazeNavigator {
    Position getEntry();
    Position getExit();
    boolean isPassage(Position pos);
    boolean inBounds(Position pos);
}
