package org.example.model;

import lombok.Getter;

@Getter
public class Game {

    private static final Game instance = new Game();

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
        return instance == null ? new Game() : instance;
    }
}
