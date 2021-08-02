import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(boolean isWhite, String newPosition) {
        this.setId(KING);
        this.setColor(isWhite);
        this.setPosition(newPosition);
        this.setHasMoved(false);
    }

    public char toChar() {
        return this.getIsWhite() ? 'K' : 'k';
    }

    public String toName() {
        return "King";
    }

    public String toString() {
        return (this.getIsWhite() ? "White" : "Black") + " king at: " + this.getPosition();
    }

    public boolean isOnPath(String endPosition) {
        int[] startPos = Chessboard.positionToInts(this.getPosition());
        int[] endPos = Chessboard.positionToInts(endPosition);
        int xDiff = Math.abs(startPos[0] - endPos[0]);
        int yDiff = Math.abs(startPos[1] - endPos[1]);
        return xDiff < 2 && yDiff < 2;
    }

    public List<int[]> getCollisionInterval(String endPosition) {
        return new ArrayList<>(0);
    }
}
