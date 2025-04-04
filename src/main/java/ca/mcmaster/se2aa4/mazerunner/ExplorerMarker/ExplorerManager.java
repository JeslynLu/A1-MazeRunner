package ca.mcmaster.se2aa4.mazerunner.ExplorerMarker;

import ca.mcmaster.se2aa4.mazerunner.MoveCommands.Command;
import ca.mcmaster.se2aa4.mazerunner.MoveCommands.CommandHistory;

public class ExplorerManager{
    private final CommandHistory history;

    public ExplorerManager(){
        this.history = new CommandHistory();
    }

    public void executeCommand(Command command){
        command.execute();
        history.push(command);
    }

    public void undo() {
        if (history.isEmpty()) {
            return;
        }
        Command prevMove = history.pop();
        if (prevMove != null) {
            prevMove.undo();
        }
    }

}