package codingWithMoshExampleOOP;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        var other = (Point)obj;
        return other.x == x && other.y == y;
    }
}
