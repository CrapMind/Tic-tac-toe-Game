package org.example.service;

import lombok.Getter;
import org.example.model.Cell;
import org.example.model.Game;
import org.example.model.util.Value;

import java.util.Arrays;

public class GameServiceImpl implements GameService {

    @Getter
    private Value winnerValue = Value.EMPTY;

    private final Cell[][] cells = Game.getInstance().getCells();

    @Override
    public void move(int x, int y, Value value) {
        cells[x][y].setValue(value);
    }

    @Override
    public boolean someWIn() {
        return winStrike(cells[0][0], cells[0][1], cells[0][2]) || winStrike(cells[1][0], cells[1][1], cells[1][2]) ||
                winStrike(cells[2][0], cells[2][1], cells[2][2]) || winStrike(cells[0][0], cells[1][1], cells[2][2]) ||
                winStrike(cells[2][0], cells[1][1], cells[0][2]) || winStrike(cells[0][0], cells[1][0], cells[2][0]) ||
                winStrike(cells[0][1], cells[1][1], cells[2][1]) || winStrike(cells[0][2], cells[1][2], cells[2][2]);
    }

    @Override
    public boolean winStrike(Cell cell1, Cell cell2, Cell cell3) {
        if (!(cell1.isEmpty())
                && cell1.getValue().equals(cell2.getValue())
                && cell2.getValue().equals(cell3.getValue())) {
            winnerValue = cell1.getValue();
            return true;
        }
        return false;
    }

    @Override
    public boolean isFinish() {
        return someWIn() || Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .noneMatch(Cell::isEmpty);
    }
}
