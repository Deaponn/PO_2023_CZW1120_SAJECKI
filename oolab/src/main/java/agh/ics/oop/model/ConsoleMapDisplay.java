package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int changesCounter = 1;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println(changesCounter + ". Map state changed: " + message);
        System.out.println(worldMap);
        changesCounter++;
    }
}
