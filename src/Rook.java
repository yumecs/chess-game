import java.util.List;
import java.util.function.Function;
import common.functional.Util;

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
        return startPos.first() == endPos.first() || startPos.second() == endPos.second();
    }

    public List<Position> getCollisionInterval(Position endPos) {
        Position startPos = this.getPosition();
        boolean vertical = startPos.first() == endPos.first();
        int startDifferent = vertical ? startPos.second() : startPos.first();
        int endDifferent = vertical ? endPos.second() : startPos.first();
        Function<Integer, Position> fn = (i) -> vertical
                ? new Position(startPos.first(), i)
                : new Position(i, startPos.second());
        return Util.map(Util.interval(startDifferent,endDifferent), fn);
    }
}
