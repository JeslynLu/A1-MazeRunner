package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Position {
    private static final Logger logger = LogManager.getLogger();
    
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX(int newX){
        this.x = newX;
    }
    public void setY(int newY){
        this.y = newY;
    }

    public void print() {
        System.out.println("Position: (" + x + ", " + y + ")");
    }
}