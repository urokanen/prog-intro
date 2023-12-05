package game;

public class RoundPosition extends TicTacToePosition {
    private final int n, m, r;

    public RoundPosition(int r) {
        super(r * 2 + 1, r * 2 + 1);
        this.n = 2 * r + 1;
        this.m = 2 * r + 1;
        this.r = r;
    }

    @Override
    public boolean isPoint(int x, int y) {
        return (x - n / 2) * (x - n / 2) + (y - m / 2) * (y - m / 2) <= r * r;
    }
}
