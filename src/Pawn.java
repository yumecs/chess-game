import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(boolean isWhite, String newPosition) {
        this.id = 0;
        this.white = isWhite;
        this.position = newPosition;
        this.hasMoved = false;
    }

    public char toChar() {
        return white ? 'P' : 'p';
    }

    public String toName() {
        return "Pawn";
    }

    public String toString() {
        return (white ? "White" : "Black") + " Pawn at " + position;
    }

    public boolean isOnPath(String end) {
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(end);
        int sign = white ? 1 : -1;
        return endPos[0] == startPos[0]
                && (endPos[1] == startPos[1] + sign || endPos[1] == startPos[1] + 2 * sign);
    }

    public List<int[]> getCollisionInterval(String end) {
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(end);
        int sign = white ? 1 : -1;
        return this.isOnPath(end) && (endPos[1] > startPos[1] + sign)
                ? Collections.singletonList(new int[]{startPos[0], startPos[1] + sign})
                : new ArrayList<>();
    }
}
