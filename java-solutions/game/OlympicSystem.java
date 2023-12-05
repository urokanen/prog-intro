package game;

import java.util.*;

public class OlympicSystem {
    private List<Integer> mas;
    private final List<List<Integer>> result;

    public OlympicSystem(int count) {
        mas = new ArrayList<>();
        result = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            mas.add(i);
        }
    }

    private boolean game(int player1, int player2) {
        System.out.print("The following players are playing: ");
        System.out.print(player1);
        System.out.print(" and ");
        System.out.println(player2);
        final Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
        int result;
        do {
            System.out.println("Enter r and k.");
            Scanner s = new Scanner(System.in);
            int r, k;
            while (true) {
                try {
                    r = Integer.parseInt(s.next());
                    k = Integer.parseInt(s.next());
                    if (r > 0 && k > 0) {
                        break;
                    }
                    System.out.println("Incorrect arguments!");
                } catch (NumberFormatException e) {
                    System.out.println("Input String cannot be parsed to Integer. Try again.");
                }
            }
            result = game.play(new RoundBoard(r, k));
            if (result == 0) {
                System.out.println("Draw, play again please!");
            }
        } while (result == 0);
        System.out.print("The winner is: ");
        System.out.println(result == 1 ? player1 : player2);
        return result == 1;
    }

    private void anotherStage() {
        Collections.shuffle(mas);
        List<Integer> winners = new ArrayList<>();
        List<Integer> losers = new ArrayList<>();
        for (int i = 0; i < mas.size(); i += 2) {
            if (game(mas.get(i), mas.get(i + 1))) {
                winners.add(mas.get(i));
                losers.add(mas.get(i + 1));
            } else {
                losers.add(mas.get(i));
                winners.add(mas.get(i + 1));
            }
        }
        mas = winners;
        result.add(losers);
    }

    public void startGame() {
        int number = 1;
        while (number * 2 <= mas.size()) {
            number *= 2;
        }
        if (number < mas.size()) {
            Collections.shuffle(mas);
            List <Integer> next = new ArrayList<>();
            List <Integer> losers = new ArrayList<>();
            for (int i = 0; i < number; i++) {
                next.add(mas.get(i));
            }
            for (int i = number; i < mas.size(); i++) {
                if (game(mas.get(i), next.get(i - number))) {
                    losers.add(next.get(i - number));
                    next.set(i - number, mas.get(i));
                } else {
                    losers.add(mas.get(i));
                }
            }
            result.add(losers);
            mas = next;
        }
        while (mas.size() > 1) {
            anotherStage();
        }
        result.add(mas);
        Collections.reverse(result);
    }

    public void getResult() {
        int place = 1;
        for (List<Integer> integers : result) {
            System.out.println("Players at place: " + place);
            place += integers.size();
            for (int element : integers) {
                System.out.print(element);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}
