package ca.mcmaster.se2aa4.mazerunner.MoveCommands;

import ca.mcmaster.se2aa4.mazerunner.MazeExplorer.Explorer;

public abstract class Command{
    protected Explorer explorer;

    public Command(Explorer explorer) {
        this.explorer = explorer;
    }

    public abstract void execute();
    public abstract void undo();
}