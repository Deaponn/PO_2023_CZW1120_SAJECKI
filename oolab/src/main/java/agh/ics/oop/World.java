package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;
import java.util.ListIterator;

public class World {
    public static void main(String[] args) {
                System.out.println("Start");
                run(OptionsParser.parseMovement(args));
                System.out.println("Stop");
    }
    public static void run(List<MoveDirection> directions) {
        ListIterator<MoveDirection> directionsList = directions.listIterator(0);

        while (directionsList.hasNext()) {
            MoveDirection currentMove = directionsList.next();
            switch (currentMove) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case TURN_LEFT -> System.out.println("Zwierzak skręca w lewo");
                case TURN_RIGHT -> System.out.println("Zwierzak skręca w prawo");
            }
        }
    }
}