package game;

public class SquareDrawChecker extends DrawChecker {
    private final int n, m;

    public SquareDrawChecker(int n, int m, int k) {
        super(n, m, k);
        this.n = n;
        this.m = m;
    }

    @Override
    public boolean isPoint(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}
