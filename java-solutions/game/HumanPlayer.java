package game;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            int r, c;
            while (true) {
                try {
                    r = Integer.parseInt(in.next());
                    c = Integer.parseInt(in.next());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Input String cannot be parsed to Integer. Try again.");
                }
            }
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }
}
