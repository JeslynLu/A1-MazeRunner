package ca.mcmaster.se2aa4.mazerunner.MazeExplorer;
import java.util.List;
import java.util.ArrayList;

public abstract class Subject{
    private final List<ExplorerObserver> observers = new ArrayList<ExplorerObserver>();

    public void attach(ExplorerObserver observer){
        this.observers.add(observer);
    }

    public void detach(ExplorerObserver observer){
        this.observers.remove(observer);
    }

    public void notifyAllObservers() {
        for(ExplorerObserver observer : this.observers) {
            observer.update();
        }
    }

    public abstract String getState();
}