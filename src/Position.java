import common.functional.Tuple;

public class Position extends Tuple<Integer> {

    public Position(int x, int y) {
        super(x, y);
    }

    public Position(String position) {
        super(parseString(position).first(), parseString(position).second());
    }

    public static Tuple<Integer> parseString(String position) {
        if (position.length() != 2) {
            //throw exception;
            return new Tuple<>(-1, 1);
        }
        int first = Character.getNumericValue(position.charAt(0)) - 10;
        int second = Character.getNumericValue(position.charAt(1)) - 1;
        return new Tuple<>(first, second);
    }

    public String toString() {
        char row = (char) (this.first() + 97);
        char col = (char) (this.second() + 49);
        return String.copyValueOf(new char[]{row, col});
    }
}
