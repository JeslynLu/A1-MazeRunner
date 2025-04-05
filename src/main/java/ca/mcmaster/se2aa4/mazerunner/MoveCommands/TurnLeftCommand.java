package ca.mcmaster.se2aa4.mazerunner.MoveCommands;

import ca.mcmaster.se2aa4.mazerunner.MazeExplorer.Explorer;

public class TurnLeftCommand implements Command{
    private final Explorer explorer;

    public TurnLeftCommand(Explorer explorer) {
        this.explorer = explorer;
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