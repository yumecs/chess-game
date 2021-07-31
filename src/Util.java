import java.util.ArrayList;
import java.util.List;

public class Util {

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

    public static List<Integer> toList(int[] arr) {
        List<Integer> list = new ArrayList<>(arr.length);
        for (int i : arr) {
            list.add(i);
        }
        return list;
    }
}
