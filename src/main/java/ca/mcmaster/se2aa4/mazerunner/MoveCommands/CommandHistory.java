package ca.mcmaster.se2aa4.mazerunner.MoveCommands;

import java.util.Stack;

public class CommandHistory {
    private final Stack<Command> history = new Stack<>();

    public void push(Command command) {
        this.history.add(command);
    }

    public Command pop() {
        return this.history.pop();
    }

    public boolean isEmpty(){
        return this.history.isEmpty();
    }
}
