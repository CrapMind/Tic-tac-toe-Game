package org.example.model;

import lombok.Getter;
import org.example.model.util.Value;

@Getter
public class Game {

    private final Cell[][] cells = new Cell[3][3];

    public void init() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }
}
