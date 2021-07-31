import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rook extends Piece {
    public Rook(boolean isWhite, String newPosition) {
        this.id = 2;
        this.white = isWhite;
        this.position = newPosition;
    }

    public char toChar() {
        return white ? 'R' : 'r';
    }

    public String toName() {
        return "Rook";
    }

    public String toString() {
        return (white ? "White" : "Black") + " rook at: " + position;
    }

    public int getPathValue(String end) {
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(end);
        if (startPos[0] == endPos[0]) {
            return 1;
        }
        if (startPos[1] == endPos[1]) {
            return 2;
        }
        return 0;
    }

    public List<int[]> getCollisionInterval(String end) {
        List<int[]> collision = new ArrayList<>(8);
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(end);
        List<Integer> range;
        switch(getPathValue(end)) {
            case(1):
                range = Util.toList(Util.range(startPos[1], endPos[1], false));;
                collision = range.stream()
                        .map((i) -> new int[]{startPos[0], i})
                        .collect(Collectors.toList());
                break;
            case(2):
                range = Util.toList(Util.range(startPos[0], endPos[0], false));;
                System.out.println(range);
                collision = range.stream()
                        .map((i) -> new int[]{i, startPos[i]})
                        .collect(Collectors.toList());
        }
        return collision;
    }
}
