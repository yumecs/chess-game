import java.util.ArrayList;
import java.util.List;

public class Util {

    public static int[] range(int a, int b) {
        return range(a, b, true);
    }
    public static int[] range(int a, int b, boolean inclusive) {
        int start = Math.min(a, b);
        int end = Math.max(a, b);
        int[] res = new int[end - start];
        for (int i = inclusive ? 0 : 1; i < res.length; i++) {
            res[i] = start + i;
        }
        return res;
    }

    public static List<Integer> toList(int[] arr) {
        List<Integer> list = new ArrayList<>(arr.length);
        for (int i : arr) {
            list.add(i);
        }
        return list;
    }
}
