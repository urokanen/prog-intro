package game;

public class SquarePosition extends TicTacToePosition {
    private final int n, m;

    public SquarePosition(int n, int m) {
        super(n, m);
        this.n = n;
        this.m = m;
    }

    @Override
    public boolean isPoint(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}
