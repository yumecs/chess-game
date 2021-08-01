import java.util.List;
import java.util.stream.Collectors;
import functional.basic.Util;

public class Bishop extends Piece {
    public Bishop(boolean isWhite, String newPosition) {
        this.id = 2;
        this.white = isWhite;
        this.position = newPosition;
    }

    public char toChar() {
        return white ? 'B' : 'b';
    }

    public String toName() {
        return "Bishop";
    }

    public String toString() {
        return (white ? "White" : "Black") + " bishop at: " + position;
    }

    public boolean isOnPath(String end) {
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(end);
        return Math.abs(startPos[0] - endPos[0]) == Math.abs(startPos[1] - endPos[1]);
    }

    public List<int[]> getCollisionInterval(String end) {
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(end);
        boolean diagonal = startPos[0] - endPos[0] == startPos[1] - endPos[1];
        int delta = startPos[1] + startPos[0] * (diagonal ? -1 : 1);
        return Util.interval(startPos[0], endPos[0])
                .stream()
                .map((i) -> new int[] {i, diagonal ? i + delta : delta - i})
                .collect(Collectors.toList());
    }
}
