package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Coordinates.Direction;
import ca.mcmaster.se2aa4.mazerunner.Coordinates.Position;

// Explorer keeps track of it's Direction and Position through the maze
public class Explorer{
    private Direction dir;
    private Position pos;

    public Explorer(Position startPos){
        this.dir = Direction.EAST; // assuming entry on always on West border
        this.pos = startPos;
    }
 
    public void moveForward() {
        this.pos = pos.move(dir);
    }

    public void moveBackward() {
        this.dir = dir.turnBack();
        moveForward();
        this.dir = dir.turnBack(); // face same direction as before
    }

    public void turnLeft() {
        this.dir = dir.turnLeft();
    }

    public void turnRight() {
        this.dir = dir.turnRight();
    }

    public Position getPosition(){
        return this.pos;
    }

    public Position getNextForwardPosition(){
        return pos.move(dir);
    }

    public Position getNextRightTurnPosition(){
        return pos.move(dir.turnRight());
    }
}