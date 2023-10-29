package main.java.agh.ics.oop;

import main.java.agh.ics.oop.model.MapDirection;
import main.java.agh.ics.oop.model.MoveDirection;
import main.java.agh.ics.oop.model.Vector2d;

public class World {
    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        System.out.println(MapDirection.SOUTH);
        System.out.println(MapDirection.EAST);
        System.out.println(MapDirection.SOUTH.next());
        System.out.println(MapDirection.SOUTH.previous());
        System.out.println(MapDirection.EAST.next());
        System.out.println(MapDirection.EAST.previous());
        System.out.println(MapDirection.SOUTH.toUnitVector());
        System.out.println(MapDirection.EAST.toUnitVector());
        //        System.out.println("Start");
        //        run(OptionsParser.parseMovement(args));
        //        System.out.println("Stop");
    }
    public static void run(MoveDirection[] directions) {
        int first_null = 0;
        for (int i = 0; i < directions.length; i++) {
            if (directions[i] == null) {
                first_null = i;
                break;
            }
        }
        for (int i = 0; i < first_null; i++) {
            boolean need_break = false;
            switch(directions[i]) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case TURN_LEFT -> System.out.println("Zwierzak skręca w lewo");
                case TURN_RIGHT -> System.out.println("Zwierzak skręca w prawo");
                default -> need_break = true;
            }
            if (need_break) break;
        }
    }
}