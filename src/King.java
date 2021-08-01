import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(boolean isWhite, String newPosition) {
        this.id = 2;
        this.isWhite = isWhite;
        this.position = newPosition;
    }

    public char toChar() {
        return isWhite ? 'K' : 'k';
    }

    public String toName() {
        return "King";
    }

    public String toString() {
        return (isWhite ? "White" : "Black") + " king at: " + position;
    }

    public boolean isOnPath(String endPosition) {
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(endPosition);
        int xDiff = Math.abs(startPos[0] - endPos[0]);
        int yDiff = Math.abs(startPos[1] - endPos[1]);
        return xDiff < 2 && yDiff < 2;
    }

    public List<int[]> getCollisionInterval(String endPosition) {
        return new ArrayList<>(0);
    }
}
