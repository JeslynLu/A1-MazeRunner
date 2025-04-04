package ca.mcmaster.se2aa4.mazerunner.MazeExplorer;

import java.util.Map;

public enum ExplorerState{
    MOVING_FORWARD,
    TURNING_LEFT,
    TURNING_RIGHT;

    private static final Map<ExplorerState, String> STATES = Map.of(
        MOVING_FORWARD, "F",
        TURNING_LEFT, "L",
        TURNING_RIGHT, "R"
    );

    public String getStateStr(){
        return STATES.get(this);
    }
}