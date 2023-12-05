package game;

abstract class DrawChecker {
    private final int n, m, k;
    private int count;
    private final Point[][]mas;

    public DrawChecker(int n, int m, int k) {
        if (n < 0 || m < 0 || k < 0) {
            throw new IllegalArgumentException("Numbers cannot be negative");
        }
        this.n = n;
        this.m = m;
        this.k = k;
        this.mas = new Point[n][m];
    }

    abstract boolean isPoint(int x, int y);

    private int isPointInt(int x, int y) {
        return isPoint(x, y) ? 1 : 0;
    }

    public boolean calc() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isPoint(i, j)) {
                    mas[i][j] = new Point(new int[]{
                            isPointInt(i - k + 1, j), isPointInt(i, j - k + 1),
                            isPointInt(i - k + 1, j - k + 1), isPointInt(i + k - 1, j - k + 1),
                            isPointInt(i + k - 1, j), isPointInt(i, j + k - 1),
                            isPointInt(i + k - 1, j + k - 1), isPointInt(i - k + 1, j + k - 1),
                    });
                    count += mas[i][j].count > 0 ? 1 : 0;
                }
            }
        }
        return count > 0;
    }

    void check(int x, int y, int pos) {
        if (isPoint(x, y)) {
            if (mas[x][y].mas[pos] == 1) {
                mas[x][y].mas[pos] = 0;
                mas[x][y].count--;
                count -= mas[x][y].count == 0 ? 1 : 0;
            }
        }
    }

    public boolean set(int x, int y) {
        if (mas[x][y].count > 0) {
            mas[x][y].count = 0;
            count--;
        }
        for (int j = 1; j < k; j++) {
            check(x - j, y, 4);
            check(x + j, y, 0);
            check(x, y - j, 5);
            check(x, y + j, 1);
            check(x + j, y + j, 2);
            check(x - j, y - j, 6);
            check(x - j, y + j, 3);
            check(x + j, y - j, 7);
        }
        return count > 0;
    }
}
