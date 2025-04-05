package ca.mcmaster.se2aa4.mazerunner.MazeExplorer;
import java.util.ArrayList;
import java.util.List;

public abstract class Subject{
    private final List<MovementObserver> observers = new ArrayList<>();

    public void attach(MovementObserver observer){
        this.observers.add(observer);
    }

    public void detach(MovementObserver observer){
        this.observers.remove(observer);
    }

    public void notifyAllObservers() {
        for(MovementObserver observer : this.observers) {
            observer.update();
        }
    }

    public abstract String getState();
}