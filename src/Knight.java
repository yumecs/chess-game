import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(boolean isWhite, String newPosition) {
        this.id = 1;
        this.isWhite = isWhite;
        this.position = newPosition;
        this.hasMoved = false;
    }

    public char toChar() {
        return isWhite ? 'N' : 'n';
    }

    public String toName() {
        return "Knight";
    }

    public String toString() {
        return (isWhite ? "White" : "Black") + " knight at: " + position;
    }

    public boolean isOnPath(String endPosition) {
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(endPosition);
        int xDiff = Math.abs(startPos[0] - endPos[0]);
        int yDiff = Math.abs(startPos[1] - endPos[1]);
        return xDiff * yDiff == 2;
    }

    public List<int[]> getCollisionInterval(String endPosition) {
        return new ArrayList<>(0);
    }
}
