package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.model.util.Value;

@Getter @Setter
public class Cell {

    private final int x;
    private final int y;

    private Value value = Value.EMPTY;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
