package ca.mcmaster.se2aa4.mazerunner.MoveCommands;

import ca.mcmaster.se2aa4.mazerunner.MazeExplorer.Explorer;

public class ForwardCommand extends Command{

    public ForwardCommand(Explorer explorer) {
        super(explorer);
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