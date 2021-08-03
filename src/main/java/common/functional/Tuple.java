package common.functional;

public class Tuple {
    private final int first;
    private final int second;

    public Tuple(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int first() {
        return this.first;
    }

    public int second() {
        return this.second;
    }
}
