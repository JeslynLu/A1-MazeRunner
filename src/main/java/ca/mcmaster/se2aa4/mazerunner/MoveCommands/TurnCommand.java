package ca.mcmaster.se2aa4.mazerunner.MoveCommands;

import ca.mcmaster.se2aa4.mazerunner.Coordinates.Direction;
import ca.mcmaster.se2aa4.mazerunner.Explorer;

public class TurnCommand extends Command{
    private Direction newDirection;

    public TurnCommand(Explorer explorer, Direction newDirection) {
        super(explorer);
        this.newDirection = newDirection;
    }

    @Override
    public void execute(){
        explorer.turnTo(newDirection);
    }

    @Override
    public void undo(){

    }
}