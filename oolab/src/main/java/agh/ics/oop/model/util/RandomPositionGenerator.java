package agh.ics.oop.model.util;

import agh.ics.oop.model.Vector2d;

import java.util.Iterator;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final RandomPosition position;
    public RandomPositionGenerator(int width, int height, int grass) {
        position = new RandomPosition(width, height, grass);
    }
    public Iterator<Vector2d> iterator() {
        return position;
    }
}
