package ca.mcmaster.se2aa4.mazerunner.MoveCommands;

import ca.mcmaster.se2aa4.mazerunner.MazeExplorer.Explorer;

public class ForwardCommand implements Command{
    private final Explorer explorer;

    public ForwardCommand(Explorer explorer) {
        this.explorer = explorer;
    }

    @Override
    public void execute(){
        explorer.moveForward();
    }

    @Override
    public void undo(){
        explorer.moveBackward();
    }
}