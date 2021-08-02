import java.util.List;
import java.util.function.Function;
import functional.basic.Util;

public class Rook extends Piece {
    public Rook(boolean isWhite, String newPosition) {
        this.setId(ROOK);
        this.setColor(isWhite);
        this.setPosition(newPosition);
        this.setHasMoved(false);
    }

    public char toChar() {
        return this.getIsWhite() ? 'R' : 'r';
    }

    public String toName() {
        return "Rook";
    }

    public String toString() {
        return (this.getIsWhite() ? "White" : "Black") + " rook at: " + this.getPosition();
    }

    public boolean isOnPath(String endPosition) {
        int[] startPos = Chessboard.positionToInts(this.getPosition());
        int[] endPos = Chessboard.positionToInts(endPosition);
        return startPos[0] == endPos[0] || startPos[1] == endPos[1];
    }

    public List<int[]> getCollisionInterval(String endPosition) {
        int[] startPos = Chessboard.positionToInts(this.getPosition());
        int[] endPos = Chessboard.positionToInts(endPosition);
        boolean vertical = startPos[0] == endPos[0];
        int different = vertical ? 1 : 0;
        Function<Integer, int[]> fn = (i) -> vertical
                ? new int[] {startPos[0], i}
                : new int[] {i, startPos[1]};
        return Util.map(Util.interval(startPos[different], endPos[different]), fn);
    }
}
