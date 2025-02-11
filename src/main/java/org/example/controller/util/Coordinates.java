package org.example.controller.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Coordinates {

    private int x;
    private int y;

    public static Coordinates getByNumber(String value) {
       return switch (value) {
            case "0" -> new Coordinates(0, 0);
            case "1" -> new Coordinates(0, 1);
            case "2" -> new Coordinates(0, 2);
            case "3" -> new Coordinates(1, 0);
            case "4" -> new Coordinates(1, 1);
            case "5" -> new Coordinates(1, 2);
            case "6" -> new Coordinates(2, 0);
            case "7" -> new Coordinates(2, 1);
            case "8" -> new Coordinates(2, 2);
            default -> throw new IllegalArgumentException("Invalid value");
        };
    }

}
