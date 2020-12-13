package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class ShapesTest {

    /**
     * after thinking, all data is from listener and do not happen some accident, so just show I could use junit test.
     */
    private Shapes line;
    private Shapes circle;

    @BeforeEach
    public void setup() {
        line = new StraightLines();
        circle = new Circles();
    }

    @Test
    void testIsClicked() {
        line.construct(0, 0, 2, 2);
        assertTrue(line.isClicked(1, 1));
    }
    @Test
    void testGetColor() {
        line.setColor(Color.green);
        assertEquals(line.getColor(), Color.green);
    }
}