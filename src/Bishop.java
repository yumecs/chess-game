import java.util.List;
import java.util.function.Function;
import functional.basic.Util;

public class Bishop extends Piece {
    public Bishop(boolean isWhite, Position newPosition) {
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

    public boolean isOnPath(Position endPos) {
        Position startPos = this.getPosition();
        return Math.abs(startPos.getX() - endPos.getX()) == Math.abs(startPos.getY() - endPos.getY());
    }

    public List<Position> getCollisionInterval(Position endPos) {
        Position startPos = this.getPosition();
        boolean diagonal = startPos.getX() - endPos.getX() == startPos.getY() - endPos.getY();
        int delta = startPos.getY() + startPos.getX() * (diagonal ? -1 : 1);
        Function<Integer, Position> fn = (i) -> new Position(i, diagonal ? i + delta : delta - i);
        return Util.map(Util.interval(startPos.getX(), endPos.getX()), fn);
    }
}
