import java.util.List;
import java.util.function.Function;
import functional.basic.Util;

public class Rook extends Piece {
    public Rook(boolean isWhite, Position newPosition) {
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

    public boolean isOnPath(Position endPos) {
        Position startPos = this.getPosition();
        return startPos.getX() == endPos.getX() || startPos.getY() == endPos.getY();
    }

    public List<Position> getCollisionInterval(Position endPos) {
        Position startPos = this.getPosition();
        boolean vertical = startPos.getX() == endPos.getX();
        int startDifferent = vertical ? startPos.getY() : startPos.getX();
        int endDifferent = vertical ? endPos.getY() : startPos.getX();
        Function<Integer, Position> fn = (i) -> vertical
                ? new Position(startPos.getX(), i)
                : new Position(i, startPos.getY());
        return Util.map(Util.interval(startDifferent,endDifferent), fn);
    }
}
