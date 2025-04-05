package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Path.PathFormatter;

class MazePathTest {
    @Test
    void getCanonical() {
        PathFormatter path = new PathFormatter("FLFFFFFRFFRFFLFFFFFFRFFFFLF");

        assertEquals("F L FFFFF R FF R FF L FFFFFF R FFFF L F", path.getCanonical());
    }

    @Test
    void getFactorized() {
        PathFormatter path = new PathFormatter("FLFFFFFRFFRFFLFFFFFFRFFFFLF");

        assertEquals("F L 5F R 2F R 2F L 6F R 4F L F", path.getFactorized());
    }

    @Test
    void expandFactorized() {
        PathFormatter path = new PathFormatter("5F 3R L 6F R F L 10F");

        assertEquals("FFFFF RRR L FFFFFF R F L FFFFFFFFFF", path.getCanonical());
    }
}