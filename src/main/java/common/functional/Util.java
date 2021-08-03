package common.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Util {
    /**
     * public static java.util.List<Integer> toList(int[] arr) {
     * java.util.List<Integer> list = new ArrayList<>(arr.length);
     * for (int i : arr) {
     * list.add(i);
     * }
     * return list;
     * }
     **/

    public static List<Integer> range(int a, int b) {
        int start = Math.min(a, b);
        int end = Math.max(a, b);
        List<Integer> res = new ArrayList<>(end - start);
        for (int i = 0; i < end - start; i++) {
            res.add(start + i);
        }
        return res;
    }

    public static List<Integer> interval(int a, int b) {
        return range(Math.min(a, b) + 1, Math.max(a, b));
    }

    public static <A, R> java.util.List<R> map(java.util.List<A> list, Function<A, R> fn) {
        return list.stream().map(fn).collect(Collectors.toList());
    }
}
