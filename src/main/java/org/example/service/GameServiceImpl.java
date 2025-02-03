package org.example.service;

import org.example.model.Cell;
import org.example.model.Game;
import org.example.model.util.Value;

public class GameServiceImpl implements GameService {

    private Value winnerValue;
    private final Cell[][] cells;

    public GameServiceImpl(Game game) {
        cells = game.getCells();
    }

    @Override
    public void move(int x, int y, Value value) {
        cells[x][y].setValue(value);
        System.out.println(cells[x][y].getValue());
        if (someWIn() || isFinish()) {
            System.out.println(someWIn() ? "Winner is : " + winnerValue : "Draw");
        }
    }
    @Override
    public boolean someWIn() {
        if (winStrike(cells[0][0], cells[0][1], cells[0][2]) || winStrike(cells[1][0], cells[1][1], cells[1][2]) ||
                winStrike(cells[2][0], cells[2][1], cells[2][2]) || winStrike(cells[0][0], cells[1][1], cells[2][2]) ||
                winStrike(cells[2][0], cells[1][1], cells[0][2]) || winStrike(cells[0][0], cells[1][0], cells[2][0]) ||
                winStrike(cells[0][1], cells[1][1], cells[2][1]) || winStrike(cells[0][2], cells[1][2], cells[2][2])) {
            return true;
        }
        return false;
    }
    @Override
    public boolean winStrike(Cell cell1, Cell cell2, Cell cell3) {
        if (cell1.getValue().equals(Value.EMPTY)) {
            return false;
        } else if (cell1.getValue().equals(cell2.getValue()) && cell2.getValue().equals(cell3.getValue())) {
            winnerValue = cell1.getValue();
            return true;
        }
        return false;
    }
    @Override
    public boolean isFinish() {
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                if (value.getValue().equals(Value.EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }
}
