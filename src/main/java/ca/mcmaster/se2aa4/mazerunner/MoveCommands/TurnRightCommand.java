package ca.mcmaster.se2aa4.mazerunner.MoveCommands;

import ca.mcmaster.se2aa4.mazerunner.MazeExplorer.Explorer;

public class TurnRightCommand implements Command{
    private final Explorer explorer;

    public TurnRightCommand(Explorer explorer) {
        this.explorer = explorer;
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