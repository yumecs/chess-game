import java.util.List;
import java.util.function.Function;
import functional.basic.Util;

public class Bishop extends Piece {
    public Bishop(boolean isWhite, String newPosition) {
        this.id = 2;
        this.isWhite = isWhite;
        this.position = newPosition;
    }

    public char toChar() {
        return isWhite ? 'B' : 'b';
    }

    public String toName() {
        return "Bishop";
    }

    public String toString() {
        return (isWhite ? "White" : "Black") + " bishop at: " + position;
    }

    public boolean isOnPath(String endPosition) {
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(endPosition);
        return Math.abs(startPos[0] - endPos[0]) == Math.abs(startPos[1] - endPos[1]);
    }

    public List<int[]> getCollisionInterval(String endPosition) {
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(endPosition);
        boolean diagonal = startPos[0] - endPos[0] == startPos[1] - endPos[1];
        int delta = startPos[1] + startPos[0] * (diagonal ? -1 : 1);
        Function<Integer, int[]> fn = (i) -> new int[] {i, diagonal ? i + delta : delta - i};
        return Util.map(Util.interval(startPos[0], endPos[0]), fn);
    }
}
