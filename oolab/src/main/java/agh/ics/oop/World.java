package main.java.agh.ics.oop;

import main.java.agh.ics.oop.model.*;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        run(OptionsParser.parseMovement(args));
        System.out.println("Stop");
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