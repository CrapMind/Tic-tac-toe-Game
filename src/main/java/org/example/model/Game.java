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

    private Value winnerValue;

    public void move(int x, int y, Value value) {
        cells[x][y].setValue(value);
    }

    public String printResult() {

        if (winStrike(cells[0][0], cells[0][1], cells[0][2]) || winStrike(cells[1][0], cells[1][1], cells[1][2]) ||
                winStrike(cells[2][0], cells[2][1], cells[2][2]) || winStrike(cells[0][0], cells[1][1], cells[2][2]) ||
                winStrike(cells[2][0], cells[1][1], cells[0][2]) || winStrike(cells[0][0], cells[1][0], cells[2][0]) ||
                winStrike(cells[0][1], cells[1][1], cells[2][1]) || winStrike(cells[0][2], cells[1][2], cells[2][2])) {
            return "Winner is : " + winnerValue;
        }
        else return "Draw";
    }

    private boolean winStrike(Cell cell1, Cell cell2, Cell cell3) {
        if (cell1.getValue().equals(Value.EMPTY)) {
            return false;
        } else if (cell1.getValue().equals(cell2.getValue()) && cell2.getValue().equals(cell3.getValue())) {
            winnerValue = cell1.getValue();
            return true;
        }
        return false;
    }

    private boolean finish() {
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                if (value.getValue().equals(Value.EMPTY)) {
                    return true;
                }
            }
        }
        return false;
    }
}
