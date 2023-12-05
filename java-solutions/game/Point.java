package game;

public class Point {
    public int[]mas;
    public int count;
    public Point(int[]mas) {
        this.mas = mas;
        for (int elem : mas) {
            count += elem;
        }
    }
}
