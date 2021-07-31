import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
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
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(end);
        boolean vertical = startPos[0] == endPos[0];
        int different = vertical ? 1 : 0;
        return Util.interval(startPos[different], endPos[different])
                .stream()
                .map((i) -> vertical
                        ? new int[] {startPos[0], i}
                        : new int[] {i, startPos[1]})
                .collect(Collectors.toList());
    }
}
