import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(boolean isWhite, Position newPosition) {
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

    public boolean isOnPath(Position endPos) {
        Position startPos = this.getPosition();
        int xDiff = Math.abs(startPos.first() - endPos.first());
        int yDiff = Math.abs(startPos.second() - endPos.second());
        return xDiff < 2 && yDiff < 2;
    }

    public List<Position> getCollisionInterval(Position endPos) {
        return new ArrayList<>(0);
    }
}
