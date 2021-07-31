import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(boolean isWhite, String newPosition) {
        this.id = 1;
        this.white = isWhite;
        this.position = newPosition;
        this.hasMoved = false;
    }

    public char toChar() {
        return white ? 'N' : 'n';
    }

    public String toName() {
        return "Knight";
    }

    public String toString() {
        return (white ? "White" : "Black") + " knight at: " + position;
    }

    public int getPathValue(String end) {
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(end);
        int xDiff = Math.abs(startPos[0] - endPos[0]);
        int yDiff = Math.abs(startPos[1] - endPos[1]);
        return xDiff * yDiff == 2 ? 1 : 0;
    }

    public List<int[]> getCollisionInterval(String end) {
        return new ArrayList<>(0);
    }
}
