package ca.mcmaster.se2aa4.mazerunner.MazeExplorer;

import ca.mcmaster.se2aa4.mazerunner.Coordinates.Direction;
import ca.mcmaster.se2aa4.mazerunner.Coordinates.Position;

// Explorer keeps track of it's Direction and Position through the maze
public class Explorer extends Subject {
    private Direction dir;
    private Position pos;
    private MovementState state;

    public Explorer(Position startPos){
        this.dir = Direction.EAST; // assuming entry on always on West border
        this.pos = startPos;
    }

    @Override
    public String getState(){
        return this.state.getStateStr();
    }

    public void setState(MovementState state){
        this.state = state;
        this.notifyAllObservers();
    }
 
    public void moveForward() {
        this.pos = pos.move(dir);
        setState(MovementState.MOVING_FORWARD);
    }

    public void turnLeft() {
        this.dir = dir.turnLeft();
        setState(MovementState.TURNING_LEFT);
    }

    public void turnRight() {
        this.dir = dir.turnRight();
        setState(MovementState.TURNING_RIGHT);
    }

    public void moveBackward() {
        this.dir = dir.turnBack();
        moveForward();
        this.dir = dir.turnBack(); // face same direction as before
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