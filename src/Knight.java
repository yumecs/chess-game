import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(boolean isWhite, String newPosition) {
        this.setId(KNIGHT);
        this.setColor(isWhite);
        this.setPosition(newPosition);
        this.setHasMoved(false);
    }

    public char toChar() {
        return this.getIsWhite() ? 'N' : 'n';
    }

    public String toName() {
        return "Knight";
    }

    public String toString() {
        return (this.getIsWhite() ? "White" : "Black") + " knight at: " + this.getPosition();
    }

    public boolean isOnPath(String endPosition) {
        int[] startPos = Chessboard.positionToInts(this.getPosition());
        int[] endPos = Chessboard.positionToInts(endPosition);
        int xDiff = Math.abs(startPos[0] - endPos[0]);
        int yDiff = Math.abs(startPos[1] - endPos[1]);
        return xDiff * yDiff == 2;
    }

    public List<int[]> getCollisionInterval(String endPosition) {
        return new ArrayList<>(0);
    }
}
