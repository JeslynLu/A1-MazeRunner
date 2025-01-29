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
    public void moveX(int newX){
        this.x += newX;
    }
    public void moveY(int newY){
        this.y += newY;
    }

    public void movePosition(int newX, int newY){
        moveX(newX);
        moveY(newY);
    }

    public Position getPosition(){
        return this;
    }

    public Position move(Direction dir){
        int newX = this.x;
        int newY = this.y;
        switch (dir) {
            case NORTH:
                newY -= 1;
                break;
            case EAST:
                newX += 1;
                break;
            case WEST:
                newX -= 1;
                break;
            case SOUTH:
                newY += 1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dir);
        }
        return new Position(newX, newY);  
    }

    public boolean equals(Position pos){
        return(this.getX() == pos.getX() && this.getY() == pos.getY());
    }

    public String print() {
        return "Position: (" + this.x + ", " + this.y + ")";
    }
}