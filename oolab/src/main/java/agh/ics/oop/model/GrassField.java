package agh.ics.oop.model;

import java.util.*;

import static java.lang.Math.*;

public class GrassField extends AbstractWorldMap implements WorldMap {
    private final Map<Vector2d, Grass> grass = new HashMap<>();
    public GrassField(int grassNumber) {
        Random randomGenerator = new Random();
        int counter = 0;
        int boundaries = (int) sqrt(10 * grassNumber);
        while (counter < grassNumber) {
            int x = randomGenerator.nextInt(boundaries + 1);
            int y = randomGenerator.nextInt(boundaries + 1);
            Vector2d position = new Vector2d(x, y);
            if (!isOccupied(position)) {
                grass.put(position, new Grass(position));
                counter++;
            }
        }
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || grass.containsKey(position);
    }
    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement animal = super.objectAt(position);
        if (animal == null) {
            return grass.get(position);
        }
        return animal;
    }
    @Override
    public String toString() {
        Set<Vector2d> allPositions = new HashSet<>();
        allPositions.addAll(animals.keySet());
        allPositions.addAll(grass.keySet());

        Iterator<Vector2d> allIterator = allPositions.iterator();
        Vector2d firstVector = allIterator.next();

        Vector2d smallVector = firstVector;
        Vector2d bigVector = firstVector;

        while (allIterator.hasNext()) {
            Vector2d nextVector = allIterator.next();
            smallVector = smallVector.lowerLeft(nextVector);
            bigVector = bigVector.upperRight(nextVector);
        }

        return internalToString(smallVector, bigVector);
    }
    @Override
    public Collection<WorldElement> getElements() {
        Collection<WorldElement> fullCollection = super.getElements();
        fullCollection.addAll(grass.values());
        return fullCollection;
    }
}
