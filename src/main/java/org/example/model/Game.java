package org.example.model;

import lombok.Getter;
import org.example.model.util.Value;

public class Game {

    private static Game instance;

    @Getter
    private final Cell[][] cells = new Cell[3][3];

    private Game() {}

    public void init() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
}
