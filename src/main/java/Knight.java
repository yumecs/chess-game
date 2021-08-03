import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(boolean isWhite, Position newPosition) {
        this.setId(KNIGHT);
        this.setColor(isWhite);
        this.setPosition(newPosition);
        this.setHasMoved(false);
    }

    public char toChar() {
        return this.getIsWhite() ? 'N' : 'n';
    }

    public String toName() {
        return "Knight";
    }

    public String toString() {
        return (this.getIsWhite() ? "White" : "Black") + " knight at: " + this.getPosition();
    }

    public boolean isOnPath(Position endPos) {
        Position startPos = this.getPosition();
        int xDiff = Math.abs(startPos.first() - endPos.first());
        int yDiff = Math.abs(startPos.second() - endPos.second());
        return xDiff * yDiff == 2;
    }

    public List<Position> getCollisionInterval(Position endPos) {
        return new ArrayList<>(0);
    }
}
