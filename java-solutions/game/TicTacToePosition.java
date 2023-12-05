package game;

import java.util.Arrays;
import java.util.Map;

abstract class TicTacToePosition implements Position {
    private static final Map<Cell, String> SYMBOLS = Map.of(
            Cell.X, "X",
            Cell.O, "O",
            Cell.E, "."
    );
    private Cell[][] cells;
    private final int n, m, sz;

    public TicTacToePosition(int n, int m) {
        if (n < 0 || m < 0) {
            throw new IllegalArgumentException("Numbers cannot be negative");
        }
        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        this.n = n;
        this.m = m;
        this.sz = Math.max(Integer.toString(n).length(), Integer.toString(m).length()) + 1;
    }

    public void set(Cell[][] cells) {
        this.cells = cells;
    }

    private String conv(String element) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sz - element.length(); i++) {
            sb.append(' ');
        }
        sb.append(element);
        return sb.toString();
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public int getM() {
        return m;
    }

    abstract boolean isPoint(int x, int y);
    @Override
    public boolean isValid(final Move move) {
        return isPoint(move.getRow(), move.getColumn()) &&
                cells[move.getRow()][move.getColumn()] == Cell.E;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(conv(" "));
        for (int c = 0; c < m; c++) {
            sb.append(conv(Integer.toString(c)));
        }
        for (int r = 0; r < n; r++) {
            sb.append("\n");
            sb.append(conv(Integer.toString(r)));
            for (int c = 0; c < m; c++) {
                if (isPoint(r, c)) {
                    sb.append(conv(SYMBOLS.get(cells[r][c])));
                } else {
                    sb.append(conv(" "));
                }
            }
        }
        return sb.toString();
    }
}
