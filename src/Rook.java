import java.util.List;
import java.util.stream.Collectors;
import functional.basic.Util;

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

    public boolean isOnPath(String end) {
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(end);
        return startPos[0] == endPos[0] || startPos[1] == endPos[1];
    }

    public List<int[]> getCollisionInterval(String end) {
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(end);
        boolean vertical = startPos[0] == endPos[0];
        int different = vertical ? 1 : 0;
        return Util.interval(startPos[different], endPos[different])
                .stream()
                .map((i) -> vertical ? new int[] {startPos[0], i} : new int[] {i, startPos[1]})
                .collect(Collectors.toList());
    }
}
