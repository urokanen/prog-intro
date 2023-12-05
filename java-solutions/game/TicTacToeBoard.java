package game;

import java.util.Arrays;

abstract class TicTacToeBoard implements Board {
    private final TicTacToePosition pos;
    private final Cell[][] cells;
    private Cell turn;
    private final int k;
    private boolean first, second;
    private final DrawChecker firstChecker, secondChecker;

    public TicTacToeBoard(int n, int m, int k, TicTacToePosition pos,
                          DrawChecker firstChecker, DrawChecker secondChecker) {
        if (n < 0 || m < 0 || k < 0) {
            throw new IllegalArgumentException("Numbers cannot be negative");
        }
        this.k = k;
        turn = Cell.X;
        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        this.pos = pos;
        this.firstChecker = firstChecker;
        first = firstChecker.calc();
        this.secondChecker = secondChecker;
        second = secondChecker.calc();
    }

    @Override
    public Position getPosition() {
        return this.pos;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    private int calc(final Move move, int val1, int val2) {
        int x = move.getRow();
        int y = move.getColumn();
        int cnt = 1;
        while (pos.isPoint(x + val1 * cnt, y + val2 * cnt)
                && cells[x + val1 * cnt][y + val2 * cnt] == move.getValue()) {
            cnt++;
        }
        return cnt;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        pos.set(cells);

        if (calc(move, -1, 1) + calc(move, 1, -1) > k
                || calc(move, 1, 1) + calc(move, -1, -1) > k
                || calc(move, 0, 1) + calc(move, 0, -1) > k
                || calc(move, -1, 0) + calc(move, 1, 0) > k) {
            return Result.WIN;
        }

        if (move.getValue() == Cell.X) {
            first = firstChecker.set(move.getRow(), move.getColumn());
        } else {
            second = secondChecker.set(move.getRow(), move.getColumn());
        }
        if (!first && !second) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    public boolean isValid(final Move move) {
        return pos.isValid(move);
    }

    @Override
    public String toString() {
        return pos.toString();
    }
}
