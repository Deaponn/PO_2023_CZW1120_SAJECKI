package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

public class RectangularMap extends AbstractWorldMap implements WorldMap {
    private final Vector2d mapStart = new Vector2d(0, 0);
    private final Vector2d mapEnd;
    public RectangularMap(int width, int height) {
        mapEnd = new Vector2d(width - 1, height - 1).add(mapStart);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(mapStart) && position.precedes(mapEnd) && super.canMoveTo(position);
    }
    @Override
    public String toString() {
        return internalToString(mapStart, mapEnd);
    }
}
