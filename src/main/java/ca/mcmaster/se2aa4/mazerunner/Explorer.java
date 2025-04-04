package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Coordinates.Direction;
import ca.mcmaster.se2aa4.mazerunner.Coordinates.Position;
import ca.mcmaster.se2aa4.mazerunner.Maze.MazeNavigator;

// Explorer keeps track of it's Direction and Position through the maze
public class Explorer{
    private Direction dir;
    private Position pos;

    public Explorer(MazeNavigator maze){
        this.dir = Direction.EAST; // assuming entry on always on West border
        this.pos = maze.getEntry();
    }
 
    public void moveForward(Position newPos) {
        this.pos = newPos;
    }

    public void turnTo(Direction newDir) {
        this.dir = newDir;
    }

    public Position getPosition(){
        return this.pos;
    }
    public Direction getDirection(){
        return this.dir;
    }
}