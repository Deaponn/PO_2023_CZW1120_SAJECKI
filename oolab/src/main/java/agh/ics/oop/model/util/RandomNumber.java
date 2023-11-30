package agh.ics.oop.model.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

// generates random integers in range [start, end)
// without duplicates
public class RandomNumber implements Iterator<Integer> {
    private final int start;
    private final int length;
    private final List<Integer> seenNumbers;
    private final Random randomGenerator = new Random();
    public RandomNumber(int start, int end) {
        this.start = start;
        this.length = end - start;
        seenNumbers = new LinkedList<Integer>();
    }
    public boolean hasNext() {
        return seenNumbers.size() < length;
    }
    // source: https://stackoverflow.com/questions/4040001/creating-random-numbers-with-no-duplicates
    public Integer next() {
        int randomNumber = start + randomGenerator.nextInt(length - seenNumbers.size());
        int output = randomNumber;
        Iterator<Integer> seenIterator = seenNumbers.iterator();
        while (seenIterator.hasNext()) {
            int nextNumber = seenIterator.next();
            if (nextNumber <= randomNumber) output++;
        }
        seenNumbers.add(output);
        return output;
    }
}
