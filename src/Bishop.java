import java.util.List;
import java.util.function.Function;

import common.functional.Util;

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
        return Math.abs(startPos.first() - endPos.first()) == Math.abs(startPos.second() - endPos.second());
    }

    public List<Position> getCollisionInterval(Position endPos) {
        Position startPos = this.getPosition();
        boolean diagonal = startPos.first() - endPos.first() == startPos.second() - endPos.second();
        int delta = startPos.second() + startPos.first() * (diagonal ? -1 : 1);
        Function<Integer, Position> fn = (i) -> new Position(i, diagonal ? i + delta : delta - i);
        return Util.map(Util.interval(startPos.first(), endPos.first()), fn);
    }
}
