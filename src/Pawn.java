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

    public int getPathValue(String end) {
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(end);
        int sign = white ? 1 : -1;
        if (endPos[0] == startPos[0]) {
            if (endPos[1] == startPos[1] + sign) {
                return 1;
            }
            else if (endPos[1] == startPos[1] + sign * 2) {
                return 2;
            }
        }
        return 0;
    }

    public List<int[]> getCollisionInterval(String end) {
        int x = Chessboard.positionToInts(position)[0];
        int y = Chessboard.positionToInts(position)[1];
        int sign = white ? 1 : -1;
        return this.getPathValue(end) == 2
                ? Collections.singletonList(new int[]{x, y + sign})
                : new ArrayList<>();
    }
}
