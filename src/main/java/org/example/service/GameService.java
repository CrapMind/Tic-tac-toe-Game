package org.example.service;

import org.example.model.Cell;
import org.example.model.util.Value;

public interface GameService {
    Value getWinnerValue();
    boolean someWIn();
    void move(int x, int y, Value value);
    boolean winStrike(Cell cell1, Cell cell2, Cell cell3);
    boolean isFinish();
}