package common.functional;

public class Tuple<T> {
    private final T first;
    private final T second;

    public Tuple(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T first() {
        return this.first;
    }

    public T second() {
        return this.second;
    }
}
