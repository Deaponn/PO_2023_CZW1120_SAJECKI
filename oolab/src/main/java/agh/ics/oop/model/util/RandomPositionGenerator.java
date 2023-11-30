package agh.ics.oop.model.util;

import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final RandomPosition position;
    public RandomPositionGenerator(int width, int height, int grass) {
        position = new RandomPosition(width, height, grass);
    }
    public Iterator iterator() {
        return position;
    }
}
