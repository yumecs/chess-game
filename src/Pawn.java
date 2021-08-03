import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(boolean isWhite, Position newPosition) {
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

    public boolean isOnPath(Position endPos) {
        Position startPos = this.getPosition();
        int sign = this.getIsWhite() ? 1 : -1;
        return endPos.getX() == startPos.getX()
                && (endPos.getY() == startPos.getY() + sign || endPos.getY() == startPos.getY() + 2 * sign);
    }

    public List<Position> getCollisionInterval(Position endPos) {
        Position startPos = this.getPosition();
        int sign = this.getIsWhite() ? 1 : -1;
        return this.isOnPath(endPos) && (endPos.getY() > startPos.getY() + sign)
                ? Collections.singletonList(new Position(startPos.getX(), startPos.getY() + sign))
                : new ArrayList<>();
    }
}
