package ca.mcmaster.se2aa4.mazerunner;


public enum Direction{ // direction you are facing
        NORTH,
        EAST,
        WEST,
        SOUTH;


    public Direction turnRight(){
        switch(this){
            case NORTH -> {
                return EAST;
                }
            case EAST -> {
                return SOUTH;
                }
            case WEST -> {
                return NORTH;
                }
            case SOUTH -> {
                return WEST;
                }
            default -> throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    public Direction turnLeft(){
        switch(this){
            case NORTH -> {
                return WEST;
                }
            case EAST -> {
                return NORTH;
                }
            case WEST -> {
                return SOUTH;
                }
            case SOUTH -> {
                return EAST;
                }
            default -> throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}

    
    
