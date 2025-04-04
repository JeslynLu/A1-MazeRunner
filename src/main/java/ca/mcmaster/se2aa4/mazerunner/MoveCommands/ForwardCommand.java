package ca.mcmaster.se2aa4.mazerunner.MoveCommands;

import ca.mcmaster.se2aa4.mazerunner.Coordinates.Position;
import ca.mcmaster.se2aa4.mazerunner.Explorer;

public class ForwardCommand extends Command{
    private Position newPosition;

    public ForwardCommand(Explorer explorer, Position newPosition) {
        super(explorer);
        this.newPosition = newPosition;
    }

    @Override
    public void execute(){
        explorer.moveForward(newPosition);
    }

    @Override
    public void undo(){

    }
}