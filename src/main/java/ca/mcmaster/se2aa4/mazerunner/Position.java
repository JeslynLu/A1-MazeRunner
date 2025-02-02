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

    public Position getPosition(){
        return this;
    }

    public Position move(Direction dir){
        int newX = this.x;
        int newY = this.y;
        switch (dir) {
            case NORTH -> newY -= 1;
            case EAST -> newX += 1;
            case WEST -> newX -= 1;
            case SOUTH -> newY += 1;
            default -> throw new IllegalStateException("Unexpected value: " + dir);
        }
        return new Position(newX, newY);  
    }

    public boolean equals(Position pos){
        return(this.getX() == pos.getX() && this.getY() == pos.getY());
    }

    public String toString() {
        return "Position: (" + this.x + ", " + this.y + ")";
    }
}