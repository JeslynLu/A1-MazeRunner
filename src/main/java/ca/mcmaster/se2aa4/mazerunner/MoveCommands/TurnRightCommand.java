package ca.mcmaster.se2aa4.mazerunner.MoveCommands;

import ca.mcmaster.se2aa4.mazerunner.Explorer;

public class TurnRightCommand extends Command{

    public TurnRightCommand(Explorer explorer) {
        super(explorer);
    }

    @Override
    public void execute(){
        explorer.turnRight();
    }

    @Override
    public void undo(){
        explorer.turnLeft();
    }
}