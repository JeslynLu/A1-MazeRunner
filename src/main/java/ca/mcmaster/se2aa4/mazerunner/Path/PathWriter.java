package ca.mcmaster.se2aa4.mazerunner.Path;
import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.se2aa4.mazerunner.MazeExplorer.MovementObserver;
import ca.mcmaster.se2aa4.mazerunner.MazeExplorer.Subject;

/**
 * Jeslyn Lu
 * lu196
 * PathWriter allows for creation of a path that represents a path through the maze using a sequence of movement instructions
 */

public class PathWriter implements MovementObserver{
    private final List<String> path = new ArrayList<>();
    private final Subject subject;

    public PathWriter(Subject subject){
        this.subject = subject;
        subject.attach(this);
    }

    // addInstruct adds inputted instruction onto end of path
    public void addInstruct(String instruct){
        path.add(instruct);
    }

    @Override
    public void update(){
        this.addInstruct(this.subject.getState());
    }

    public PathFormatter getPath(){
        StringBuilder pathString = new StringBuilder("");
        for(String c : path){
            pathString.append(c);
        }
        return new PathFormatter(pathString.toString());
    }

}