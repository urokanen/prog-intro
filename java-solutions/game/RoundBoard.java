package game;

public class RoundBoard extends TicTacToeBoard {
    public RoundBoard(int r, int k) {
        super(2 * r + 1, 2 * r + 1, k, new RoundPosition(r),
                new RoundDrawChecker(r, k), new RoundDrawChecker(r, k));
    }
}
