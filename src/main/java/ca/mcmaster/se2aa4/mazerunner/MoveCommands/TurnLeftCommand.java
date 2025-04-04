package ca.mcmaster.se2aa4.mazerunner.MoveCommands;

import ca.mcmaster.se2aa4.mazerunner.ExplorerMarker.Explorer;

public class TurnLeftCommand extends Command{

    public TurnLeftCommand(Explorer explorer) {
        super(explorer);
    }

    @Override
    public void execute(){
        explorer.turnLeft();
    }

    @Override
    public void undo(){
        explorer.turnRight();
    }
}