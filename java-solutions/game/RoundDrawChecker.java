package game;

public class RoundDrawChecker extends DrawChecker {
    private final int n, m, r;

    public RoundDrawChecker(int r, int k) {
        super(r * 2 + 1, r * 2 + 1, k);
        this.n = r * 2 + 1;
        this.m = r * 2 + 1;
        this.r = r;
    }

    @Override
    public boolean isPoint(int x, int y) {
        return (x - n / 2) * (x - n / 2) + (y - m / 2) * (y - m / 2) <= r * r;
    }
}
