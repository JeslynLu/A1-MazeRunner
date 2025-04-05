package ca.mcmaster.se2aa4.mazerunner.Path;
import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.se2aa4.mazerunner.MazeExplorer.MovementObserver;
import ca.mcmaster.se2aa4.mazerunner.MazeExplorer.Subject;


public class PathWriter implements MovementObserver{
    private List<String> path = new ArrayList<>();
    private Subject subject;

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