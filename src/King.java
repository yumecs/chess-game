import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(boolean isWhite, String newPosition) {
        this.id = 2;
        this.white = isWhite;
        this.position = newPosition;
    }

    public char toChar() {
        return white ? 'K' : 'k';
    }

    public String toName() {
        return "King";
    }

    public String toString() {
        return (white ? "White" : "Black") + " king at: " + position;
    }

    public int getPathValue(String end) {
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(end);
        int xDiff = Math.abs(startPos[0] - endPos[0]);
        int yDiff = Math.abs(startPos[1] - endPos[1]);
        return xDiff * yDiff < 2 ? 1 : 0;
    }

    public List<int[]> getCollisionInterval(String end) {
        return new ArrayList<>(0);
    }
}
