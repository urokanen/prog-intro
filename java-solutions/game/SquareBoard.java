package game;

public class SquareBoard extends TicTacToeBoard {
    public SquareBoard(int n, int m, int k) {
        super(n, m, k, new SquarePosition(n, m),
                new SquareDrawChecker(n, m, k), new SquareDrawChecker(n, m, k));
    }
}
