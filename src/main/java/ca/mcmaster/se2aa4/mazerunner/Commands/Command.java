package ca.mcmaster.se2aa4.mazerunner.Commands;

public interface Command{

    public abstract void execute();

    public abstract void undo();
}