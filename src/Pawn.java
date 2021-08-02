import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(boolean isWhite, String newPosition) {
        this.setId(PAWN);
        this.setColor(isWhite);
        this.setPosition(newPosition);
        this.setHasMoved(false);
    }

    public char toChar() {
        return this.getIsWhite() ? 'P' : 'p';
    }

    public String toName() {
        return "Pawn";
    }

    public String toString() {
        return (this.getIsWhite() ? "White" : "Black") + " Pawn at " + this.getPosition();
    }

    public boolean isOnPath(String endPosition) {
        int[] startPos = Chessboard.positionToInts(this.getPosition());
        int[] endPos = Chessboard.positionToInts(endPosition);
        int sign = this.getIsWhite() ? 1 : -1;
        return endPos[0] == startPos[0]
                && (endPos[1] == startPos[1] + sign || endPos[1] == startPos[1] + 2 * sign);
    }

    public List<int[]> getCollisionInterval(String endPosition) {
        int[] startPos = Chessboard.positionToInts(this.getPosition());
        int[] endPos = Chessboard.positionToInts(endPosition);
        int sign = this.getIsWhite() ? 1 : -1;
        return this.isOnPath(endPosition) && (endPos[1] > startPos[1] + sign)
                ? Collections.singletonList(new int[]{startPos[0], startPos[1] + sign})
                : new ArrayList<>();
    }
}
