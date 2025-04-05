package ca.mcmaster.se2aa4.mazerunner.MoveCommands;

public interface Command{
    public abstract void execute();
    public abstract void undo();
}