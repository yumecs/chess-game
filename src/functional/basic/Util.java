package functional.basic;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Util {
    public static java.util.List<Integer> toList(int[] arr) {
        java.util.List<Integer> list = new ArrayList<>(arr.length);
        for (int i : arr) {
            list.add(i);
        }
        return list;
    }

    public static List<Integer> range(int a, int b) {
        int start = Math.min(a, b);
        int end = Math.max(a, b);
        int[] res = new int[end - start];
        for (int i = 0; i < res.length; i++) {
            res[i] = start + i;
        }
        return toList(res);
    }

    public static List<Integer> interval(int a, int b) {
        return range(Math.min(a,b) + 1, Math.max(a, b));
    }

    public static <V, T> java.util.List<T> map (java.util.List<V> list, Function<V, T> fn) {
        return list.stream().map(fn).collect(Collectors.toList());
    }
}
