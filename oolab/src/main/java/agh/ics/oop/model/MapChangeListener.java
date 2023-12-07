package agh.ics.oop.model;

public interface MapChangeListener {
    void mapChanged(WorldMap worldMap, String message);

    @Override
    boolean equals(Object other);
}
