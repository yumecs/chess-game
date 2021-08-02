import java.util.List;
import java.util.function.Function;
import functional.basic.Util;

public class Bishop extends Piece {
    public Bishop(boolean isWhite, String newPosition) {
        this.setId(BISHOP);
        this.setColor(isWhite);
        this.setPosition(newPosition);
        this.setHasMoved(false);
    }

    public char toChar() {
        return this.getIsWhite() ? 'B' : 'b';
    }

    public String toName() {
        return "Bishop";
    }

    public String toString() {
        return (this.getIsWhite() ? "White" : "Black") + " bishop at: " + this.getPosition();
    }

    public boolean isOnPath(String endPosition) {
        int[] startPos = Chessboard.positionToInts(this.getPosition());
        int[] endPos = Chessboard.positionToInts(endPosition);
        return Math.abs(startPos[0] - endPos[0]) == Math.abs(startPos[1] - endPos[1]);
    }

    public List<int[]> getCollisionInterval(String endPosition) {
        int[] startPos = Chessboard.positionToInts(this.getPosition());
        int[] endPos = Chessboard.positionToInts(endPosition);
        boolean diagonal = startPos[0] - endPos[0] == startPos[1] - endPos[1];
        int delta = startPos[1] + startPos[0] * (diagonal ? -1 : 1);
        Function<Integer, int[]> fn = (i) -> new int[] {i, diagonal ? i + delta : delta - i};
        return Util.map(Util.interval(startPos[0], endPos[0]), fn);
    }
}
